package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Bingo;
import ch.hackathon.backend.models.Card;
import ch.hackathon.backend.repositories.BingoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class BingoService {
    private final BingoRepository bingoRepository;

    public Bingo createBingo(int width, int height, Set<Card> cardPool) {

        //Get cards and shuffle them.
        List<Card> cards = new ArrayList<Card>(cardPool);
        Collections.shuffle(cards);
        //Truncate List to first width*heigth cards.
        cards = cards.subList(0, width*height);

        //Create Bingo
        Bingo bingo = new Bingo(null, width, height, cards, new ArrayList<>(), new ArrayList<>());

        return bingoRepository.save(bingo);
    }
}
