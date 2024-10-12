package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Game;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.LectureTimeframe;
import ch.hackathon.backend.models.Professor;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.GameRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;


    public Game createGame(Lecture lecture, Professor professor, LectureTimeframe timeframe) {
        Game game = new Game(null, lecture, professor, timeframe);
        return gameRepository.save(game);
    }

    public Optional<Game> getGame(User user, long lecture_id) {
        return gameRepository.findById(lecture_id);
    }
}
