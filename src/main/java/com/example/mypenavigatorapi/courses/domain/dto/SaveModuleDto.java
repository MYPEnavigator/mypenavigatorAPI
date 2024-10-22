package com.example.mypenavigatorapi.courses.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveModuleDto {
    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String imageUrl;

    @NotNull
    @NotBlank
    private String materialUrl;

    @NotNull
    @NotBlank
    private String videoUrl;

    @NotNull
    private Integer order;
}
