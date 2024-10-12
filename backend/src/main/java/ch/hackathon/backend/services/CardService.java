package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Card;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.Professor;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.CardRepository;
import ch.hackathon.backend.repositories.LectureRepository;
import ch.hackathon.backend.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;
    private final LectureRepository lectureRepository;
    private final ProfessorRepository professorRepository;

    public Card createCard(User creator, String text, long lectId, long profId) {
        Instant creationDate = Instant.now(); //Allocates the current date.
        Set<User> upvotes = new LinkedHashSet<>();
        Set<User> downvotes = new LinkedHashSet<>();

        //Get Optionals from Ids.
        Optional<Lecture> optLect =  lectureRepository.findById(lectId);
        Optional<Professor> optProf = professorRepository.findById(profId);

        //Get Lecture, or throw an Exception.
        Lecture lecture = optLect.orElseThrow(() -> new IllegalArgumentException("No Lecture with given lectId exists!"));
        Professor prof = optProf.orElseThrow(() -> new IllegalArgumentException("No Professor with given profId exists!"));

        //Create card and save it in the db.
        Card card = new Card(null, text, creationDate, creator,
                upvotes, downvotes, lecture, prof);
        return cardRepository.save(card);
    }

    /**
     * Cast a vote for a card. Throws a IllegalArgumentException if there's no card with cardId.
     */
    public void vote(User user, Long cardId, Vote vote){
        //Get card from id.
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if(optionalCard.isPresent()){
            Card card = optionalCard.get();

            //Get the upvote and download sets, and change them accordingly to "vote".
            Set<User> upvotes = card.getUpvotes();
            Set<User> downvotes = card.getDownvotes();
            if(vote == Vote.UP){
                downvotes.remove(user);
                upvotes.add(user);
            }
            else if(vote == Vote.DOWN){
                upvotes.remove(user);
                downvotes.add(user);
            }

            //Save changed card in the db.
            cardRepository.save(card);
        }
        else{
            //Give feedback, that there's no card with the given id.
            throw new IllegalArgumentException("Card not found");
        }
    }

    /**
     * Gets all Cards of a lecture out of the db.
     */
    public Collection<Card> getCardsOfLecture(long lectId) {
        return cardRepository.findByLecture_Id(lectId);
    }
}
