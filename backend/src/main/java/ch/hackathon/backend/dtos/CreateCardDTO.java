package ch.hackathon.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCardDTO {
    private String text;
    private long lectId;
    private long profId;
}
