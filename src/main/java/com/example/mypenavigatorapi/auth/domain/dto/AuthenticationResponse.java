package com.example.mypenavigatorapi.auth.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserDto user;
}
