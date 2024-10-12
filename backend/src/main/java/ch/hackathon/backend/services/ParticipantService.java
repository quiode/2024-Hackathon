package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Bingo;
import ch.hackathon.backend.models.Card;
import ch.hackathon.backend.models.Participant;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final BingoService bingoService;

    public Participant createParticipant(User user, int bingoWidth, int bingoHeight, Set<Card> cardPool) {
        Bingo bingo = bingoService.createBingo(bingoWidth, bingoHeight, cardPool);
        Participant participant = new Participant(null, user, bingo);
        return participantRepository.save(participant);
    }
}
