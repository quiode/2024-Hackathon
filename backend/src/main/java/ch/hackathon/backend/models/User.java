package ch.hackathon.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
  @ManyToMany
  @JsonIgnore // TODO
  private Set<Lecture> lectures;

  /**
   *
   */
  @ManyToOne
  @JsonIgnore // TODO
  private Game currentGame;
}
