package ch.hackathon.backend.models;

import java.util.Date;
import java.util.Set;

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
/*
 * A Bingo `Card`, i.e. an entry in the Bingo (that was created as reference to
 * a lecture / prof)
 */
public class Card {
  @Id
  @GeneratedValue
  private Long id;
  private String text;
  private Date creationDate;
  /**
   * The user that designed this card
   */
  private User creator;
  /**
   * We save all users that upvoted this post to
   * be able to prevent multiple upvotes
   */
  private Set<User> upvotes;
  /**
   * We save all users that downvoted this post to
   * be able to prevent multiple downvotes
   */
  private Set<User> downvotes;
  /**
   * Cards are always created for specific lectures
   */
  private Lecture lecture;
  /**
   * Cards are always references to a specific professors habits, words etc.
   */
  private Professor professor;

}
