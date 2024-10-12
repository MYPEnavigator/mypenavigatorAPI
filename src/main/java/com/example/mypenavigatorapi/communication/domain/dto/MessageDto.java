package com.example.mypenavigatorapi.communication.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {
    private Long id;
    private String content;
    private Date sentAt;
    private UserDto sender;
}
