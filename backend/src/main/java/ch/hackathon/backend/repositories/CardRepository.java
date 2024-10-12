package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
