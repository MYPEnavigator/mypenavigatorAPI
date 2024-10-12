package com.example.mypenavigatorapi.communication.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveNotificationDto {
    @NotNull
    @NotBlank
    private String text;
}
