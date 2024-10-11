package ch.hackathon.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
  @ManyToOne
  private Lecture lecture;

  @ManyToOne
  private Professor professor;

  @ManyToOne
  private LectureTimeframe timeframe;

}
