package ch.hackathon.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
