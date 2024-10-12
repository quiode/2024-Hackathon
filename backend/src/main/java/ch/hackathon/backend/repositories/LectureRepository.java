package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.LectureTimeframe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
  // List<Lecture> findAllByProfessors(Professor professor);
  Optional<Lecture> findByName(String name);
  Optional<Lecture> findByDates(LectureTimeframe timeframe);
  Optional<Lecture> findByDatesIn(Collection<LectureTimeframe> timeframes)
}
