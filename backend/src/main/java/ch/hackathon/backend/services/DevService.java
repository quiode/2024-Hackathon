package ch.hackathon.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.UserRepository;

@RequiredArgsConstructor
@Service
/**
 * Initializes the database with example data
 * 
 * This class should NEVER be present in production
 */ 
public class DevService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final LectureService lectureService;
    private final GameService gameService;

   

    public void generateFakeDate() {
        
    }

}
