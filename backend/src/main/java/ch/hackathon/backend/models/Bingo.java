package ch.hackathon.backend.models;

import java.util.List;

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
/**
 * An actual Bingo card that was generated during a game for a specific user
 *
 * The actual card grid is stored as row major matrix (see width and height
 * attributes)
 */
public class Bingo {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  private User user;
  @ManyToOne
  private Game game;
  @Column(nullable = false)
  private Integer height;
  @Column(nullable = false)
  private Integer width;

  /**
   * The card grid for the Bingo card (In ROW-MAJOR order as heightXwidth matrix)
   */
  @ManyToMany
  private List<Card> cardMatrix;
}
