package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {


}