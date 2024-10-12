package com.example.mypenavigatorapi.communication.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMessageDto {
    @NotNull
    @NotBlank
    private String content;

    @NotNull
    @Positive
    private Long senderId;

    @NotNull
    @Positive
    private Long conversationId;
}
