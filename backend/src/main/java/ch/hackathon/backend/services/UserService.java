package ch.hackathon.backend.services;

import ch.hackathon.backend.models.UserM;
import ch.hackathon.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  /**
   * Finds a user by email and returns am empty optional if not found
   */
  public Optional<UserM> findUserByMail(String mail) {
    return userRepository.findByMail(mail);
  }

  /**
   * Creates a user with specified values and returns it
   */
  public UserM createUser(String name, String mail) {
    // check if user already exists
    if (findUserByMail(mail).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User with that mail already exists");
    }

    UserM user = new UserM(null, name, mail);

    return userRepository.save(user);
  }
}
