package ch.hackathon.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor {
  public Professor(String name) {
    this.id = null;
    this.name = name;
  }

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;
}
