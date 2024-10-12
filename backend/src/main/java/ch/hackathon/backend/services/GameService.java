package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Game;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.LectureTimeframe;
import ch.hackathon.backend.models.Professor;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.GameRepository;
import ch.hackathon.backend.repositories.LectureTimeframeRepository;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final LectureTimeframeRepository lectureTimeframeRepository;


    public Game createGame(Lecture lecture, Professor professor, LectureTimeframe timeframe) {
        Game game = new Game(null, lecture, professor, timeframe, new HashSet<>());
        return gameRepository.save(game);
    }

    public Optional<Game> getGame(User user, long lecture_id) {
        return gameRepository.findById(lecture_id);
    }

    public Optional<Game> getCurrentGameForUser(User user) {
        Instant now = Instant.now();
        List<LectureTimeframe> potentialTimeframes = lectureTimeframeRepository.findAllByStartDateBeforeAndEndDateAfter(now, now);
        for (var tf: potentialTimeframes) {
            List<Game> potentialGames = gameRepository.findAllByLectureTimeframe(tf);
            for (Game game: potentialGames) {
                if (game.getUsers().stream().anyMatch(u -> u.getId() == user.getId())) {
                    return Optional.of(game);
                }
            }
        }
        return Optional.empty();
    }
}
