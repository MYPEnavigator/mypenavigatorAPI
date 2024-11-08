package com.example.mypenavigatorapi.rewards.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveBankRewardDto {
    private boolean active;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Integer requiredPoints;

    @NotNull
    @NotBlank
    private String imageUrl;
}
