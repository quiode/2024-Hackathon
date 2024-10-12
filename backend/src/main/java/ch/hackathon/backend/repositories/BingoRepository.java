package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.Bingo;
import ch.hackathon.backend.models.Game;
import ch.hackathon.backend.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BingoRepository extends JpaRepository<Bingo, Long> {
  List<Bingo> findAllByGame(Game game);
  Optional<Bingo> findByGameAndUser(Game game, User user);
}
