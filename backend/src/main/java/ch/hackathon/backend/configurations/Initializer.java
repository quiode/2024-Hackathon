package ch.hackathon.backend.configurations;

import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.repositories.ProfessorRepository;
import ch.hackathon.backend.repositories.UserRepository;
import ch.hackathon.backend.services.CardService;
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

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
    private final CardService cardService;

    @Value("${PROD:false}")
    private boolean prod;


    @EventListener(ApplicationReadyEvent.class)
    public void generateFakeData() {
        if (!prod) {
            try {
                // Create users
                List<User> users = new ArrayList<>();
                users.add(userService.createUser("Dominik Schwaiger", "dschwaiger@student.ethz.ch"));
                users.add(userService.createUser("Benedikt Baumgarten", "bbaumgarten@student.ethz.ch"));
                users.add(userService.createUser("Ferdinand Pamberger", "fpamberger@student.ethz.ch"));
                users.add(userService.createUser("Felix Kurz", "fekurz@student.ethz.ch"));
                users.add(userService.createUser("Dziyana Azaronak", "dazaronak@student.ethz.ch"));

                // Create lectures
                // Disk Math
                Instant eight = Instant.now()
                        .atZone(ZoneOffset.UTC)
                        .withHour(8)
                        .withMinute(0)
                        .toInstant();
                Instant twenty = eight.plus(12, ChronoUnit.HOURS);
                Lecture ueli = lectureService.createLecture(
                        "Diskrete Mathematik",
                        new HashSet<>(Arrays.asList("Ueli Maurer")),
                        new HashSet<>(Arrays.asList(Pair.with(eight, twenty)))
                );
                // LinAlg
                Instant six = Instant.now()
                        .atZone(ZoneOffset.UTC)
                        .withHour(6)
                        .withMinute(0)
                        .toInstant();
                Lecture linalg = lectureService.createLecture(
                        "Lineare Algebra",
                        new HashSet<>(Arrays.asList("Alfonso Bandeira", "Bernd Gärtner")),
                        new HashSet<>(Arrays.asList(Pair.with(six, eight)))
                );
                // AnD
                Instant twentytwo = Instant.now()
                        .atZone(ZoneOffset.UTC)
                        .withHour(22)
                        .withMinute(0)
                        .toInstant();
                Lecture and = lectureService.createLecture(
                        "Algorithmen und Datenstrukturen",
                        new HashSet<>(Arrays.asList("Johannes Lengler")),
                        new HashSet<>(Arrays.asList(Pair.with(twenty, twentytwo)))
                );

                for (final User user : users) {
                    user.getLectures().add(ueli);
                    user.getLectures().add(linalg);
                    user.getLectures().add(and);
                }
                userRepository.saveAll(users);

                // add cards to ueli
                cardService.createCard(users.getFirst(), "Glasklar!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Quantenquark!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Thermodramatik!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Chaosologe!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Entropieparty!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Schwerkraftspaß!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Mathegedöns!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Exergieerleuchtung!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Kollapskomödie!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Funktionstüftelei!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Gravitationsgiggle!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Entropieschock!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Unendlichkeitskaffee!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Kausalitätsknoten!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Heisenbergsche Hopplahopp!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Wellenchaos!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Datenkrümel!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Kollisionskünstler!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Energiefalle!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Wärmeparadox!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());
                cardService.createCard(users.getFirst(), "Sinnfrequenz!", ueli.getId(),
                        ueli.getProfessors().stream().findFirst().orElseThrow().getId());

            } catch (Exception e) {
                log.info(e.getMessage());
                log.error(
                        "Some fake data is already in the database, please delete database for correct initialization!");
            }
        }
    }
}
