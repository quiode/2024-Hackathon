package ch.hackathon.backend.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
  
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "lecture_id", nullable = false)
  private Lecture lecture;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "lecture_timeframe_id", nullable = false)
  private LectureTimeframe lectureTimeframe;

}
