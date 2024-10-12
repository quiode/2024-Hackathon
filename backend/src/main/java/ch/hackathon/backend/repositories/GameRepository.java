package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Game;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.LectureTimeframe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
  Optional<Game> findByLectureTimeframeAndLecture(LectureTimeframe timeframe, Lecture lecture);
  /**
   * The timeframe is a database entry which is uniquely mapped to the lecture
   */ 
  Optional<Game> findByLectureTimeframe(LectureTimeframe timeframe);

}
