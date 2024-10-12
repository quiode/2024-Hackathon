package ch.hackathon.backend.configurations;

import ch.hackathon.backend.repositories.UserRepository;
import ch.hackathon.backend.services.GameService;
import ch.hackathon.backend.services.LectureService;
import ch.hackathon.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Initializes the database with example data
 *
 * This class should NEVER be present in production
 */
@Component
@RequiredArgsConstructor
public class DevService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final LectureService lectureService;
    private final GameService gameService;

    @Value("${PROD:false}")
    private boolean prod;


    @EventListener(ApplicationReadyEvent.class)
    public void generateFakeData() {
        if (!prod) {

        }
    }

}
