package com.example.mypenavigatorapi.enrollments.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SaveCourseCompletionHistoryDto {

    @NotNull
    @Positive
    private Integer resultPercentage;

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long courseId;
}
