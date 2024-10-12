package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Card;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.Professor;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;


    public Card createCard(User creator, String text, Lecture lecture, Professor professor) {
        Date creationDate = new Date(); //Allocates the current date.
        Set<User> upvotes = new HashSet<>();
        Set<User> downvotes = new HashSet<>();

        Card card = new Card(null, text, creationDate, creator,
                upvotes, downvotes, lecture, professor);
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
