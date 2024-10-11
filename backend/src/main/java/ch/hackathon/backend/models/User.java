package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String mail;

  /**
   * The lectures this student attends
   */
  @Column(nullable = false)
  @ManyToMany
  private Set<Lecture> lectures = new HashSet<>();

  /**
   *
   */
  @ManyToOne
  private Game currentGame;
}
