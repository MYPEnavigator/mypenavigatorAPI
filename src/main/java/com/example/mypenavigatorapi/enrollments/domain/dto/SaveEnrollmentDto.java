package com.example.mypenavigatorapi.enrollments.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveEnrollmentDto {
    private Boolean isCompleted = false;
    private Integer progress = 0;
    private String certificateUrl = "";

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long courseId;
}
