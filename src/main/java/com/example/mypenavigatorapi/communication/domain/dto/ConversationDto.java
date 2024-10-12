package com.example.mypenavigatorapi.communication.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConversationDto {
    private Long id;
    private List<MessageDto> messages;
    private List<ConversationParticipantDto> participants;
}
