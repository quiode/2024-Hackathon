package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
/**
 * Represents a Lecture (not a specific date, but rather for a whole semester)
 *
 */
public class Lecture {
 public Lecture(String name, Set<Professor> professors, Set<LectureTimeframe> datesLectureTimeframes) {
  id = null;
  this.name = name;
  this.professors = professors;
  this.dates = datesLectureTimeframes;
 }
 
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  /**
   * the professors holding this lecture
   * 
   * must contain at least one element
   */
  @ManyToMany
  private Set<Professor> professors;

  /**
   * we create a separate LectureTimeframe model to make handling
   * the time frames easier (we don't want deeply nested collections)
   * 
   * Note that the timeframe should uniquely belong to a specific lecture
   *
   */
  @OneToMany(cascade = CascadeType.ALL)
  private Set<LectureTimeframe> dates;

}
