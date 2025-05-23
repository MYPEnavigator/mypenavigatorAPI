package com.example.mypenavigatorapi.communication.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConversationDto {
    private Long id;

    private UserInfoDto firstParticipant;
    private UserInfoDto secondParticipant;

    private List<MessageDto> messages;
}
