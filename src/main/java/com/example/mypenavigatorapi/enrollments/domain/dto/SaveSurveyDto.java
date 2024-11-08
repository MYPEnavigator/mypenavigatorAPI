package com.example.mypenavigatorapi.enrollments.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveSurveyDto {

    @NotEmpty
    private List<SaveSurveyQuestionResponseDto> responses;
}
