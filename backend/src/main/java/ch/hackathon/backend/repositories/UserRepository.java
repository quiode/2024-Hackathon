package ch.hackathon.backend.repositories;

import ch.hackathon.backend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByMail(String mail);
}