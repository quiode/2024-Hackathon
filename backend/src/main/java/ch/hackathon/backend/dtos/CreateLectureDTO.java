package ch.hackathon.backend.dtos;

import java.time.Instant;
import java.util.Set;

import org.javatuples.Pair;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateLectureDTO {
    private String name;
    private Set<String> professors;
    private Set<Pair<Instant, Instant>> dates;
}
