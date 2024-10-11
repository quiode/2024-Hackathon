package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserTable")
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
  // @Column(nullable = false)
  // @ManyToMany
  // private Set<Lecture> lectures = new HashSet<>();

  /**
   *
   */
  // @ManyToOne
  // private Game currentGame;
}
