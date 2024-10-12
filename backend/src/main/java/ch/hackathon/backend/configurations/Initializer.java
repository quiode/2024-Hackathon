package ch.hackathon.backend.configurations;

import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.ProfessorRepository;
import ch.hackathon.backend.repositories.UserRepository;
import ch.hackathon.backend.services.GameService;
import ch.hackathon.backend.services.LectureService;
import ch.hackathon.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Initializes the database with example data
 * <p>
 * This class should NEVER be present in production
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class Initializer {
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
                userService.createUser("Dominik Schwaiger", "dschwaiger@ethz.ch");
            } catch (ResponseStatusException e) {
                log.info("Did not repopulate database with fake user, as it already exists");
            }

            User user = userService.findUserByMail("dschwaiger@ethz.ch").orElseThrow();

            try {
                Lecture ueli = lectureService.createLecture(
                        "Mittelschule",
                        new HashSet<>(Arrays.asList("Ueli Maurer")),
                        new HashSet<>(Arrays.asList(Pair.with(Instant.now(), Instant.now().plus(10, ChronoUnit.HOURS))))
                        // new HashSet<>(), new HashSet<>()
                );
                user.getLectures().add(ueli);
                user = userRepository.save(user);
            } catch (IllegalArgumentException e) {
                log.info("Did not repopulate database with fake lecture, as it already exists.");
            }
            ;


            Lecture and = null;
            try {
                and = lectureService.createLecture(
                        "Algorithmische Verbesserungsanstalt",
                        new HashSet<>(Arrays.asList("Johannes Lengler")),
                        new HashSet<>(Arrays.asList(Pair.with(Instant.now().plus(15, ChronoUnit.HOURS),
                                Instant.now().plus(17, ChronoUnit.HOURS))))
                );
                user.getLectures().add(and);
                user = userRepository.save(user);
            } catch (IllegalArgumentException e) {
                log.info("Did not repopulate database with fake lecture, as it already exists.");
            }

            Lecture anw = null;
            try {
                anw = lectureService.createLecture(
                        "Schroedingers Algorithmen",
                        new HashSet<>(Arrays.asList("Angelika Steger")),
                        new HashSet<>(Arrays.asList(Pair.with(Instant.now().plus(15, ChronoUnit.HOURS),
                                Instant.now().plus(17, ChronoUnit.HOURS))))
                );
                user.getLectures().add(anw);
                user = userRepository.save(user);
            } catch (IllegalArgumentException e) {
                log.info("Did not repopulate database with fake lecture, as it already exists.");
            }

            Lecture linAlg = null;
            try {
                linAlg = lectureService.createLecture(
                        "Skinny Tall vs. Short Wide",
                        new HashSet<>(Arrays.asList("Afonsa Bandeira")),
                        new HashSet<>(Arrays.asList(Pair.with(Instant.now().plus(15, ChronoUnit.HOURS),
                                Instant.now().plus(17, ChronoUnit.HOURS))))
                );
                user.getLectures().add(linAlg);
                user = userRepository.save(user);
            } catch (IllegalArgumentException e) {
                log.info("Did not repopulate database with fake lecture, as it already exists.");
            }

            Lecture eProg = null;
            try {
                eProg = lectureService.createLecture(
                        "How To Get Sellerie",
                        new HashSet<>(Arrays.asList("Thomas Gross")),
                        new HashSet<>(Arrays.asList(Pair.with(Instant.now().plus(15, ChronoUnit.HOURS),
                                Instant.now().plus(17, ChronoUnit.HOURS))))
                );
                user.getLectures().add(eProg);
                user = userRepository.save(user);
            } catch (IllegalArgumentException e) {
                log.info("Did not repopulate database with fake lecture, as it already exists.");
            }

            if (and != null) {
                try {
                    gameService.createGame(and, and.getProfessors().stream().findFirst().get(),
                            and.getDates().stream().findFirst().get());
                } catch (IllegalArgumentException e) {
                    log.info("Did not repopulate database with fake game, as it already exists.");
                }
            }

            


            
        }
    }

}
