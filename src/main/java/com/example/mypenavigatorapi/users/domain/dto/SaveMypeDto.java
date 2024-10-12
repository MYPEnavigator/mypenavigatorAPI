package com.example.mypenavigatorapi.users.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMypeDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 11, max = 11, message = "RUC must be 11 characters")
    private String ruc;
}
