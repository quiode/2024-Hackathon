package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Bingo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BingoRepository extends JpaRepository<Bingo, Long> {

}
