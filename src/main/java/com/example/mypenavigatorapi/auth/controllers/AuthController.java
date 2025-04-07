package com.example.mypenavigatorapi.auth.controllers;

import com.example.mypenavigatorapi.auth.domain.dto.AuthenticationLogin;
import com.example.mypenavigatorapi.auth.domain.dto.AuthenticationResponse;
import com.example.mypenavigatorapi.auth.domain.dto.GoogleAuthenticationLogin;
import com.example.mypenavigatorapi.auth.services.AuthenticationService;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "auth", description = "authentication endpoints")
public class AuthController {
    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    @Operation(summary = "Login with credentials")
    public AuthenticationResponse login(
            @RequestBody AuthenticationLogin request
    ) {
        return authService.authenticate(Mapper.map(request, User.class));
    }

    @PostMapping("/login-with-google")
    @Operation(summary = "Login with credentials")
    public AuthenticationResponse loginWithGoogle(
            @RequestBody GoogleAuthenticationLogin request
    ) {
        return authService.loginWithGoogle(request);
    }

}
