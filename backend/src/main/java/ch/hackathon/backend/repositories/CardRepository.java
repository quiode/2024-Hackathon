package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Card;

import ch.hackathon.backend.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CardRepository extends JpaRepository<Card, Long> {

    Collection<Card> findByLecture_Id(Long id);
}