package com.example.mypenavigatorapi.courses.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveQuestionDto {
    @NotNull
    @NotBlank
    private String questionText;

    @NotNull
    private Boolean multiple;

    @NotNull
    private List<SaveAnswerDto> answers;
}
