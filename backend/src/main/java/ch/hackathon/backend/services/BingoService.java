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

    public Bingo createBingo(int width, int height, List<Card> cardPool) {

        //Get cards and shuffle them.
        List<Card> cards = new ArrayList<>(cardPool);
        Collections.shuffle(cards);
        //Truncate List to first width*heigth cards.
        cards = cards.subList(0, width*height);

        //Create Bingo
        List<Integer> ntValidated = new ArrayList<>(width*height);
        for(int i = 0; i <= width*height; i++){
            ntValidated.add(0);
        }
        Bingo bingo = new Bingo(null, width, height, cards, ntValidated);

        return bingoRepository.save(bingo);
    }
}
