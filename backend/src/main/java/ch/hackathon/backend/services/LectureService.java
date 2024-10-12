package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.LectureTimeframe;
import ch.hackathon.backend.models.Professor;
import ch.hackathon.backend.repositories.LectureRepository;
import ch.hackathon.backend.repositories.LectureTimeframeRepository;
import ch.hackathon.backend.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LectureService {
  private final LectureRepository lectureRepository;
  private final ProfessorRepository professorRepository;
  private final LectureTimeframeRepository lectureTimeframeRepository;

   /**
   * Creates a with specified values and returns it
   */
  public Lecture createLecture(String name, Set<String> professorNames, Set<Pair<Instant, Instant>> dates) {
    // check if lecture already exists
    if (lectureRepository.findByName(name).isPresent()) {
      throw new IllegalArgumentException("Lecture with this name already exists");
    }
    // get professors from DB or create them if they do not exist
    Set<Professor> professors = new HashSet<>(); 
    for (String profName: professorNames) {
      Optional<Professor> queryResult = professorRepository.findByName(profName);
      queryResult.ifPresentOrElse(
        prof -> professors.add(prof),
        () -> {
          Professor prof = new Professor(profName);
          professors.add(
            professorRepository.save(prof)
          );
        }
      );
    }

    // create the timeframes that the lectures are in
    Set<LectureTimeframe> datesTimeframe = new HashSet<>();
    for (Pair<Instant, Instant> date: dates) {
      LectureTimeframe tf = new LectureTimeframe(date.getValue0(), date.getValue1());
      datesTimeframe.add(
        lectureTimeframeRepository.save(tf)
      );
    }



    // Lecture user = new Lecture(name, professors, datesTimeframe);
    Lecture lecture = new Lecture(name, new HashSet<>(), new HashSet<>());
    Lecture lectureWithId = lectureRepository.save(lecture);
    lectureWithId.setProfessors(professors);
    lectureWithId.setDates(datesTimeframe);
    return lectureRepository.save(lectureWithId);    // return null;
  }

  public Optional<Lecture> getById(Long id) { return lectureRepository.findById(id); }
  public Optional<LectureTimeframe> getTimeframeForLecture(Lecture lecture, Instant date) {
    return lectureTimeframeRepository.findAllByStartDateBeforeAndEndDateAfter(date, date)
      .stream()
      .filter(tf -> {
          Optional<Lecture> lectureOpt = lectureRepository.findByDates(tf);
          return lectureOpt.isPresent() && lectureOpt.get().getId().equals(lecture.getId());
        }
      ).findAny();
      
  }
}
