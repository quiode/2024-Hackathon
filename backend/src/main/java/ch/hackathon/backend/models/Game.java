package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "lecture_id", nullable = false)
  private Lecture lecture;

  @ManyToOne(optional = false)
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;

  @ManyToOne(optional = false)
  @JoinColumn(name = "lecture_timeframe_id", nullable = false)
  private LectureTimeframe lectureTimeframe;

}
