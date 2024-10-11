package ch.hackathon.backend.services;

import ch.hackathon.backend.models.User;
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
    public Optional<User> findUserByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    /**
     * Creates a user with specified values and returns it
     */
    public User createUser(String name, String mail) {
        // check if user already exists
        if (findUserByMail(mail).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with that mail already exists");
        }

        User user = new User(null, name, mail, new HashSet<>(), null);

        return userRepository.save(user);
    }
}
