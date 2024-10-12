package com.example.mypenavigatorapi.courses.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    private Long id;
    private String answerText;
    private Boolean isCorrect;
}
