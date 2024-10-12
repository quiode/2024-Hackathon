package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
