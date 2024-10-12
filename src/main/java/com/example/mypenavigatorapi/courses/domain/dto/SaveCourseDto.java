package com.example.mypenavigatorapi.courses.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCourseDto {
    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String description;

    @NotNull
    private Integer rewardPoints;

    @NotNull
    @NotBlank
    private String imageUrl;

    @NotNull
    @NotBlank
    private String videoUrl;
}
