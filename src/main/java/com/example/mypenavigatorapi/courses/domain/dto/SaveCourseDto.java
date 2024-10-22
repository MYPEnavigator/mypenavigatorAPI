package com.example.mypenavigatorapi.courses.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveCourseDto {
    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String slug;

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

    @NotNull
    @NotBlank
    private String managerName;

    @NotNull
    @NotBlank
    private String signatureUrl;

    @NotNull
    @NotBlank
    private String level;

    @NotNull
    @NotEmpty
    private List<String> syllabus;
}
