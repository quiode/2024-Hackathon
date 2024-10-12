package ch.hackathon.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LectureTimeframe {
  public LectureTimeframe(Instant start, Instant end) {
    id = null;
    startDate = start;
    endDate = end;
  }
  
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private Instant startDate;

  @Column(nullable = false)
  private Instant endDate;
}
