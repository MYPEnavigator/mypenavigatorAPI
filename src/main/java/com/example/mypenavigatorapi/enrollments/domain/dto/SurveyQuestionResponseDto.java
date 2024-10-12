package com.example.mypenavigatorapi.enrollments.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyQuestionResponseDto {
    private Long id;
    private String question;
    private Integer rating;
}
