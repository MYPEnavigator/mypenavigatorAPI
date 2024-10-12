package com.example.mypenavigatorapi.courses.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMaterialDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String url;

    @NotNull
    @NotBlank
    private String type;
}
