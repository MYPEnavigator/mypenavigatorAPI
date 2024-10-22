package com.example.mypenavigatorapi.auth.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationLogin {
    private String email;
    private String password;
}
