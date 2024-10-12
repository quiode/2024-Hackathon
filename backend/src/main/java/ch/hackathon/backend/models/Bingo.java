package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

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

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false)
  private Game game;

  @Column(nullable = false)
  private Integer height;

  @Column(nullable = false)
  private Integer width;

  /**
   * The card grid for the Bingo card (In ROW-MAJOR order as heightXwidth matrix)
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "Bingo_cardMatrix",
          joinColumns = @JoinColumn(name = "bingo_id"),
          inverseJoinColumns = @JoinColumn(name = "cardMatrix_id"))
  private Set<Card> cardMatrix = new LinkedHashSet<>();

}
