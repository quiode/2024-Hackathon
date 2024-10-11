package ch.hackathon.backend.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

/**
 * Represents a Lecture (not a specific date, but rather for a whole semester)
 *
 */
public class Lecture {

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
   */
  @ManyToMany(cascade = CascadeType.ALL)
  private Set<LectureTimeframe> dates;

}
