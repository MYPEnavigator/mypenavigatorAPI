package com.example.mypenavigatorapi.communication.domain.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveConversationDto {
    @NotNull
    @Positive
    private Long firstParticipantId;

    @NotNull
    @Positive
    private Long secondParticipantId;
}
