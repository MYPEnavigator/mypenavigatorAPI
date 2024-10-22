package com.example.mypenavigatorapi.courses.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String questionText;
    private Boolean multiple;
    private List<AnswerDto> answers;
}
