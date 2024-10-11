package ch.hackathon.backend.services;

import ch.hackathon.backend.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public Optional<User> findUserByMail(String mail) {
        // TODO: find user by mail

        return Optional.empty();
    }
}
