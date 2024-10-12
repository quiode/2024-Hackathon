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
    public Lecture createLecture(@RequestAttribute User creator,
                                 @RequestBody CreateLectureDTO lecture) {
        Lecture createdLecture = lectureService.createLecture(lecture.getName(), lecture.getProfessors(), lecture.getDates());
        return createdLecture;
    }

    @GetMapping("/{lectId}")
    public Lecture getLecture(@PathVariable Long lectId) {
        Optional<Lecture> lecture = lectureService.getLectureById(lectId);
        if (lecture.isPresent()) {
            return lecture.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public Collection<Lecture> getLectures(@RequestAttribute User user) {
        return user.getLectures();
    }
}
