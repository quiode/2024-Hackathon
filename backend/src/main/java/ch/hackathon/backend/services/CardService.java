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

        Optional<Lecture> optLect =  lectureRepository.findById(lectId);
        Optional<Professor> optProf = professorRepository.findById(profId);

        if(optLect.isEmpty()) {
            throw new IllegalArgumentException("No Lecture with given lectId exists!");
        }
        if(optProf.isEmpty()) {
            throw new IllegalArgumentException("No Professor with given profId exists!");
        }
        Lecture lecture = optLect.get();
        Professor prof = optProf.get();

        Card card = new Card(null, text, creationDate, creator,
                upvotes, downvotes, lecture, prof);
        return cardRepository.save(card);
    }

    /*
     * Cast a vote for a card. Throws a IllegalArgumentException.
     */
    public void vote(User user, Long cardId, Vote vote){
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if(optionalCard.isPresent()){
            Card card = optionalCard.get();
            Set<User> upvotes = card.getUpvotes();
            Set<User> downvotes = card.getDownvotes();
            if(vote == Vote.UP){
                upvotes.add(user);
                downvotes.remove(user);
            }
            else if(vote == Vote.DOWN){
                downvotes.add(user);
                upvotes.remove(user);
            }
        }
        else{
            //Give feedback, that there's no card with the given id.
            throw new IllegalArgumentException("Card not found");
        }
    }

}
