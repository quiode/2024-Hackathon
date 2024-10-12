package ch.hackathon.backend.controllers;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ch.hackathon.backend.dtos.CreateLectureDTO;
import ch.hackathon.backend.models.Lecture;
import ch.hackathon.backend.models.User;
import ch.hackathon.backend.services.LectureService;

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

    @GetMapping("/")
    public Lecture getLecture(@RequestBody Long id) {
        Optional<Lecture> lecture = lectureService.getLectureById(id);
        if (lecture.isPresent()) {
            return lecture.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
