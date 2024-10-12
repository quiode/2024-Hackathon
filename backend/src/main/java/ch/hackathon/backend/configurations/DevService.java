package ch.hackathon.backend.configurations;

import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.repositories.ProfessorRepository;
import ch.hackathon.backend.repositories.UserRepository;
import ch.hackathon.backend.services.GameService;
import ch.hackathon.backend.services.LectureService;
import ch.hackathon.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.javatuples.Pair;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

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
@Slf4j
public class DevService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final ProfessorRepository professorRepository;
    private final LectureService lectureService;
    private final GameService gameService;

    @Value("${PROD:false}")
    private boolean prod;


    @EventListener(ApplicationReadyEvent.class)
    public void generateFakeData() {
        if (!prod) {
            try {
               lectureService.createLecture(
                   "Mittelschule", 
                   new HashSet<>(Arrays.asList("Ueli Maurer")), 
                   new HashSet<>(Arrays.asList(Pair.with(Instant.now(), Instant.now().plus(10, ChronoUnit.HOURS))))
                   // new HashSet<>(), new HashSet<>()
               );
            } catch (IllegalArgumentException e) {
              log.info("Did not repopulate database with fake data, as it already exists.");          
              return;
            };

           Lecture and = lectureService.createLecture(
               "Algorithmische Verbesserungsanstalt", 
               new HashSet<>(Arrays.asList("Johannes Lengler")), 
               new HashSet<>(Arrays.asList(Pair.with(Instant.now().plus(15, ChronoUnit.HOURS), Instant.now().plus(17, ChronoUnit.HOURS))))
           );
           gameService.createGame(and, and.getProfessors().stream().findFirst().get(), and.getDates().stream().findFirst().get());
        }
    }

}
