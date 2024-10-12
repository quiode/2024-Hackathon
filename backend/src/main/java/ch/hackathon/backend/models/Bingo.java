package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

  @Column(nullable = false)
  private Integer height;

  @Column(nullable = false)
  private Integer width;

  /**
   * The card grid for the Bingo card (In ROW-MAJOR order as heightXwidth matrix)
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "Bingo_cards",
          joinColumns = @JoinColumn(name = "bingo_id"),
          inverseJoinColumns = @JoinColumn(name = "cards_id"))
  private List<Card> cards = new ArrayList<>();

}
