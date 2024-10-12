package ch.hackathon.backend.controllers;

import ch.hackathon.backend.dtos.CreateLectureDTO;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.services.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @PostMapping("/create")
    public Lecture createLecture(@RequestAttribute User user,
                                 @RequestBody CreateLectureDTO lecture) {
        Lecture createdLecture = lectureService.createLecture(lecture.getName(), lecture.getProfessors(), lecture.getDates());
        return createdLecture;
    }

    /**
     * @return the lecture with id lectId if the student takes the lecture
     */
    @GetMapping("/{lectId}")
    public Lecture getLecture(@RequestAttribute User user, @PathVariable Long lectId) {
        return user.getLectures().stream().filter(lecture -> lecture.getId().equals(lectId)).findFirst().orElse(null);
    }

    @GetMapping("")
    public Collection<Lecture> getLectures(@RequestAttribute User user) {
        return user.getLectures();
    }
}
