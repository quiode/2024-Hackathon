package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.UserM;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserM, Long> {
  Optional<UserM> findByMail(String mail);
}
