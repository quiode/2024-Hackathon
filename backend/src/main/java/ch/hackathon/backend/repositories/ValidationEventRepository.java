package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.ValidationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationEventRepository extends JpaRepository<ValidationEvent, Long> {

}
