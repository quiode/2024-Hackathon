package ch.hackathon.backend.services;

import ch.hackathon.backend.models.UserM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  public Optional<UserM> findUserByMail(String mail) {
    // TODO: find user by mail

    return Optional.empty();
  }
}
