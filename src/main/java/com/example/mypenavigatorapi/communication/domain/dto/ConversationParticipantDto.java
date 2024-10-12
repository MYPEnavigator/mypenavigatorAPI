package com.example.mypenavigatorapi.communication.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConversationParticipantDto {
    private Long id;
    private Date joinedAt;
    private UserDto user;
}
