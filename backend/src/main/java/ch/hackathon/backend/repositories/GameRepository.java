package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.*;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
  Optional<Game> findByLectureTimeframeAndLecture(LectureTimeframe timeframe, Lecture lecture);
  /**
   * The timeframe is a database entry which is uniquely mapped to the lecture
   */ 
  List<Game> findAllByLectureTimeframe(LectureTimeframe timeframe);

}
