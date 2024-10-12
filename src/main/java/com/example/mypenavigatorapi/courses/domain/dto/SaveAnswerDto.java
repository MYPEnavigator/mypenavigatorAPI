package com.example.mypenavigatorapi.courses.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveAnswerDto {
    @NotNull
    @NotBlank
    private String answerText;

    @NotNull
    private Boolean isCorrect;
}
