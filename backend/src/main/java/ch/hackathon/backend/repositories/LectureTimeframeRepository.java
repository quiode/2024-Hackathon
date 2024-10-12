package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.LectureTimeframe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface LectureTimeframeRepository extends JpaRepository<LectureTimeframe, Long> {
  /**
   * finds all lecture time frames that contain the timeframe (date1, date2) 
   * 
   * to find all timeframes that contain a date, just set date1 == date2
   */
  List<LectureTimeframe> findAllByStartDateBeforeAndEndDateAfter(Instant date1, Instant date2);
}
