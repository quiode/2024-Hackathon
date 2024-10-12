package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * An actual Bingo card that was generated during a game for a specific user
 * The actual card grid is stored as row major matrix (see width and height
 * attributes)
 */
public class Bingo {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private Integer width;

  @Column(nullable = false)
  private Integer height;



  /**
   * The card grid for the Bingo card (In ROW-MAJOR order as heightXwidth matrix)
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "Bingo_cards",
          joinColumns = @JoinColumn(name = "bingo_id"),
          inverseJoinColumns = @JoinColumn(name = "cards_id"))
  private List<Card> cards = new ArrayList<>();

  /**
   * Card was marked by the user and still is.
   */
  @ElementCollection
  @Column(name = "marked")
  @CollectionTable(name = "Bingo_marked", joinColumns = @JoinColumn(name = "owner_id"))
  private List<Boolean> marked = new ArrayList<>();

  /**
   * The number of times a card was marked by a user and validated: Often only 0 or 1.
   * (Idea: "ntValidated.get(index)++;" if validated)
   */
  @ElementCollection
  @Column(name = "nt_validated")
  @CollectionTable(name = "Bingo_ntValidated", joinColumns = @JoinColumn(name = "owner_id"))
  private List<Integer> ntValidated = new ArrayList<>();

}
