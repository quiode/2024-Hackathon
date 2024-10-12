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
}
