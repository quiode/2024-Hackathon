package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
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
  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Professor> professors;

  /**
   * we create a separate LectureTimeframe model to make handling
   * the time frames easier (we don't want deeply nested collections)
   * 
   * Note that the timeframe should uniquely belong to a specific lecture
   *
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<LectureTimeframe> dates;


  /**
   * GENERATED BY INTELLIJ-SHIT, do not edit or try to understand xD
   */
 @Override
 public final boolean equals(Object o) {
  if (this == o) return true;
  if (o == null) return false;
  Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
  Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
  if (thisEffectiveClass != oEffectiveClass) return false;
  Lecture lecture = (Lecture) o;
  return getId() != null && Objects.equals(getId(), lecture.getId());
 }
 /**
  * GENERATED BY INTELLIJ-SHIT, do not edit or try to understand xD
  */
 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
 }
}
