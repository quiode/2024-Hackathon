package ch.hackathon.backend.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String mail;
  /**
   * The lectures this student attends
   */
  @Column(nullable = false)
  @ManyToMany()
  private Set<Lecture> lectures;

  /**
   *
   */
  @ManyToOne()
  private Game currentGame;

}
