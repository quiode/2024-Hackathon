package ch.hackathon.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

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

  @Column(nullable = false)
  private String text;

  @Column(nullable = false)
  @CreatedDate
  private Date creationDate;

  /**
   * The user that designed this card
//    */
//   @ManyToOne
//   private UserM creator;

//   /**
//    * We save all users that upvoted this post to
//    * be able to prevent multiple upvotes
//    */
//   @ManyToMany
//   private Set<UserM> upvotes;

//   /**
//    * We save all users that downvoted this post to
//    * be able to prevent multiple downvotes
//    */
//   @ManyToMany
//   private Set<UserM> downvotes;

//   /**
//    * Cards are always created for specific lectures
//    */
//   @ManyToOne
//   private Lecture lecture;

//   /**
//    * Cards are always references to a specific professors habits, words etc.
//    */
//   @ManyToOne
//   private Professor professor;
}
