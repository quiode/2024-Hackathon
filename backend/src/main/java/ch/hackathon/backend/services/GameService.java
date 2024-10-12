package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Card;
import ch.hackathon.backend.models.Game;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.LectureTimeframe;
import ch.hackathon.backend.models.Professor;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.CardRepository;
import ch.hackathon.backend.repositories.GameRepository;
import ch.hackathon.backend.repositories.LectureRepository;
import ch.hackathon.backend.repositories.LectureTimeframeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
@Slf4j
public class GameService {
    private static final int BINGO_SIZE = 4;
    private final GameRepository gameRepository;
    private final LectureTimeframeRepository lectureTimeframeRepository;
    private final CardRepository cardRepository;
    private final LectureRepository lectureRepository;


    public Optional<Game> getById(long id) { return gameRepository.findById(id); };


    public Optional<Game> createGame(Lecture lecture, Professor professor, LectureTimeframe timeframe) {
        long cardCount = cardRepository.count();
        if (cardCount == 0)
            return Optional.empty();
        if (cardCount < BINGO_SIZE * BINGO_SIZE)
            log.warn("Not enough cards ({}) to fill Bingo({}x{}), using duplicates.", cardCount, BINGO_SIZE, BINGO_SIZE);
        // FIXME: this is terribly inefficient
        List<Card> cards = cardRepository.findAll();
        cards.sort(new Comparator<Card>() {
            public int compare(Card c1, Card c2) {
                int voteDeltaC1 = c1.getUpvotes().size() - c1.getDownvotes().size();
                int voteDeltaC2 = c2.getUpvotes().size() - c2.getDownvotes().size();
                return voteDeltaC2 - voteDeltaC1;
            }
        });
        List<Card> cardMatrix = cards.stream().limit(BINGO_SIZE * BINGO_SIZE).collect(Collectors.toCollection(ArrayList::new));
        while (cardMatrix.size() < BINGO_SIZE * BINGO_SIZE) {
            cardMatrix.add(cards.getFirst());
        }

        
        Game game = new Game(null, lecture, professor, timeframe, new HashSet<>(), new HashSet<>(cardMatrix), BINGO_SIZE, BINGO_SIZE);
        return Optional.of(gameRepository.save(game));
    }

    public Optional<Game> getGame(User user, long lecture_id) {
        return gameRepository.findById(lecture_id);
    }

    public Optional<Game> getCurrentGameForLecture(Lecture lecture) {
        Instant now = Instant.now();
        List<LectureTimeframe> potentialLectures = lectureTimeframeRepository.findAllByStartDateBeforeAndEndDateAfter(now, now);
        Optional<LectureTimeframe> currentTimeframe = potentialLectures.stream()
            .filter(tf -> {
                Optional<Lecture> l = lectureRepository.findByDates(tf);
                return l.isPresent() && l.get().getId().equals(lecture.getId());
            })
            .findFirst();
        if (currentTimeframe.isEmpty()) return Optional.empty();
        Optional<Game> game = gameRepository.findByLectureTimeframeAndLecture(currentTimeframe.get(), lecture);
        return game;
    }

    public Optional<Game> getCurrentGameForUser(User user) {
        Instant now = Instant.now();
        List<LectureTimeframe> potentialTimeframes = lectureTimeframeRepository.findAllByStartDateBeforeAndEndDateAfter(now, now);
        for (var tf: potentialTimeframes) {
            List<Game> potentialGames = gameRepository.findAllByLectureTimeframe(tf);
            for (Game game: potentialGames) {
                if (game.getUsers().stream().anyMatch(u -> u.getId().equals(user.getId()))) {
                    return Optional.of(game);
                }
            }
        }
        return Optional.empty();
    }
}
