package ch.hackathon.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LectureTimeframe {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private Date startDate;

  @Column(nullable = false)
  private Date endDate;
}
