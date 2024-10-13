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
public class Game {
  
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "lecture_id", nullable = false)
  private Lecture lecture;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "lecture_timeframe_id", nullable = false)
  private LectureTimeframe lectureTimeframe;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "game_participants",
          joinColumns = @JoinColumn(name = "participant_id"),
          inverseJoinColumns = @JoinColumn(name = "game_id"))
  private Set<Participant> participants;

  @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false)
  private Set<ValidationEvent> validationEvents = new LinkedHashSet<>();

  @ManyToMany
  @JoinTable(name = "Game_cardPool",
          joinColumns = @JoinColumn(name = "game_id"),
          inverseJoinColumns = @JoinColumn(name = "cardPool_id"))
  private List<Card> cardPool = new ArrayList<>();

  @Column(name = "bingoWidth", nullable = false)
  private Integer bingoWidth;

  @Column(name = "bingoHeight", nullable = false)
  private Integer bingoHeight;

}
