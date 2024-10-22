package com.example.mypenavigatorapi.auth.services;

import com.example.mypenavigatorapi.auth.domain.dto.AuthenticationResponse;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.domain.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository usersRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository usersRepository, JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.usersRepository = usersRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        Optional<User> user = usersRepository.findByEmail(request.getUsername());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = jwtService.generateToken(user.get());

        return new AuthenticationResponse(
                token,
                Mapper.map(user.get(), UserDto.class)
        );
    }
}
