package ch.hackathon.backend.services;

import ch.hackathon.backend.models.*;
import ch.hackathon.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class GameService {
    private static final int BINGO_SIZE = 4;
    private static final double VALIDATION_PERCENT_NEEDED = 0.5;
    private static final long VALIDATION_TIME_SECONDS = 20;
    private final GameRepository gameRepository;
    private final LectureTimeframeRepository lectureTimeframeRepository;
    private final CardRepository cardRepository;
    private final LectureRepository lectureRepository;
    private final ValidationEventRepository validationEventRepository;
    private final BingoRepository bingoRepository;

    //Used for clickCard.
    private final Lock lock = new ReentrantLock();

    public Optional<Game> getById(long id) {
        return gameRepository.findById(id);
    }

    ;


    public Optional<Game> createGame(Lecture lecture, Professor professor, LectureTimeframe timeframe) {
        long cardCount = cardRepository.count();
        if (cardCount == 0) return Optional.empty();
        if (cardCount < BINGO_SIZE * BINGO_SIZE)
            log.warn("Not enough cards ({}) to fill Bingo({}x{}), using duplicates.", cardCount, BINGO_SIZE,
                    BINGO_SIZE);
        // FIXME: this is terribly inefficient
        List<Card> cards = cardRepository.findAll();
        cards.sort((c1, c2) -> {
            int voteDeltaC1 = c1.getUpvotes().size() - c1.getDownvotes().size();
            int voteDeltaC2 = c2.getUpvotes().size() - c2.getDownvotes().size();
            return voteDeltaC2 - voteDeltaC1;
        });
        List<Card> cardMatrix = cards.stream()
                .limit(BINGO_SIZE * BINGO_SIZE)
                .collect(Collectors.toCollection(ArrayList::new));
        while (cardMatrix.size() < BINGO_SIZE * BINGO_SIZE) {
            cardMatrix.add(cards.getFirst());
        }


        Game game = new Game(null, lecture, professor, timeframe, new HashSet<>(), new HashSet<>(),
                cardMatrix, BINGO_SIZE, BINGO_SIZE);
        return Optional.of(gameRepository.save(game));
    }

    public Optional<Game> getGame(User user, long lecture_id) {
        return gameRepository.findById(lecture_id);
    }

    public Optional<Game> getCurrentGameForLecture(Lecture lecture) {
        Instant now = Instant.now();
        List<LectureTimeframe> potentialLectures = lectureTimeframeRepository.findAllByStartDateBeforeAndEndDateAfter(
                now, now);
        Optional<LectureTimeframe> currentTimeframe = potentialLectures.stream().filter(tf -> {
            Optional<Lecture> l = lectureRepository.findByDates(tf);
            return l.isPresent() && l.get().getId().equals(lecture.getId());
        }).findFirst();
        if (currentTimeframe.isEmpty()) return Optional.empty();
        Optional<Game> game = gameRepository.findByLectureTimeframeAndLecture(currentTimeframe.get(), lecture);
        return game;
    }

    public Optional<Game> getCurrentGameForUser(User user) {
        Instant now = Instant.now();
        List<LectureTimeframe> potentialTimeframes = lectureTimeframeRepository.findAllByStartDateBeforeAndEndDateAfter(
                now, now);
        for (var tf : potentialTimeframes) {
            List<Game> potentialGames = gameRepository.findAllByLectureTimeframe(tf);
            for (Game game : potentialGames) {
                if (game.getParticipants().stream().anyMatch(u -> u.getUser().getId().equals(user.getId()))) {
                    return Optional.of(game);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Handles that a card was clicked in a bingo (board).
     * Pre: There is a current game running for the given user.
     *
     * @param user The user that clicked the card.
     * @param pos  The position of the card in the bingo table.
     */
    public void clickCard(User user, Integer pos) {
        lock.lock(); //Lock our private lock, to ensure no weird things will happen.
        try {
            //Note: this method relies on the assumption, that only one thread at a time executes it.

            //Get Game, Participant and card.
            Optional<Game> optGame = getCurrentGameForUser(user);
            Game game = optGame.orElseThrow(() -> new IllegalArgumentException("No current Game with given User"));
            Participant p = game.getParticipants()
                    .stream()
                    .filter(u -> u.getUser().getId().equals(user.getId()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
            Card card = p.getBingo().getCards().get(pos);

            //Get ValidationEvent, or create a new one.
            ValidationEvent valEv;
            Optional<ValidationEvent> optValEv = game.getValidationEvents()
                    .stream()
                    .filter(v -> v.getCard().equals(card))
                    .findFirst();
            valEv = optValEv.orElseGet(
                    () -> new ValidationEvent(null, card, VALIDATION_PERCENT_NEEDED, VALIDATION_TIME_SECONDS,
                            Instant.now(), new HashSet<>()));

            //Check if time window is over.
            Instant endTime = valEv.getStartTime().plusSeconds(valEv.getPeriodSeconds());
            if (endTime.isAfter(Instant.now())) {
                //Reset valEv.
                valEv = new ValidationEvent(null, card, VALIDATION_PERCENT_NEEDED, VALIDATION_TIME_SECONDS,
                        Instant.now(), new HashSet<>());

                //Add user to clickedCards of valEv, and save valEv.
                valEv.getClickedCard().add(user);
                validationEventRepository.save(valEv);
                return;
            }

            //Time Window is still active.
            //Check if threshold amount of users already reached.
            if (valEv.getClickedCard().size() >= valEv.getPercentNeeded() * game.getParticipants().size()) {
                //Thus only update Bingo of given player.
                //Increment value at pos and save the bingo.
                List<Integer> ntVal = p.getBingo().getNtValidated();
                ntVal.set(pos, ntVal.get(pos) + 1);
                bingoRepository.save(p.getBingo());

                //Add user to clickedCards of valEv, and save valEv.
                valEv.getClickedCard().add(user);
                validationEventRepository.save(valEv);
                return;
            }

            //Threshold amount of users not yet reached.
            //Add user to clickedCards of valEv, and save valEv.
            valEv.getClickedCard().add(user);
            validationEventRepository.save(valEv);
            //Check again if threshold amount reached now.
            if (valEv.getClickedCard().size() >= valEv.getPercentNeeded() * game.getParticipants().size()) {
                //The given user is the threshold user: Validate all clicks that happened since event was set up.
                incCardValidated(valEv.getClickedCard(), card, game);
            }
        } finally {
            lock.unlock(); //Unlock our private lock.
        }
    }

    /**
     * Saves, that the given card has been validated, for all given users.
     * Does this by incrementing the corresponding ntValidates-entry of the given card in the users bingo.
     */
    private void incCardValidated(Set<User> users, Card card, Game game) {
        //Get all the participants corresponding to the given users.
        List<Participant> incParticipants = game.getParticipants()
                .stream()
                .filter(p -> users.contains(p.getUser()))
                .toList();
        for (Participant p : incParticipants) {
            //Get position of bingoCard, and increment the value of ntValidated.
            int pos = p.getBingo().getCards().indexOf(card);
            List<Integer> ntVal = p.getBingo().getNtValidated();
            ntVal.set(pos, ntVal.get(pos) + 1);
            bingoRepository.save(p.getBingo());
        }
    }
}
