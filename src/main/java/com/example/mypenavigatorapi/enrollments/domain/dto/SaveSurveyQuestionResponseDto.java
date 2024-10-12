package com.example.mypenavigatorapi.enrollments.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSurveyQuestionResponseDto {
    @NotNull
    @NotBlank
    private String question;

    @NotNull
    @NotBlank
    private String answer;
}
