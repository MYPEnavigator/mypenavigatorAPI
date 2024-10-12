package com.example.mypenavigatorapi.rewards.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveBankRewardDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private Integer requiredPoints;

    @NotNull
    @NotBlank
    private String imageUrl;
}
