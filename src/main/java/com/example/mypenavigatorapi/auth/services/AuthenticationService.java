package com.example.mypenavigatorapi.auth.services;

import com.example.mypenavigatorapi.auth.domain.dto.AuthenticationResponse;
import com.example.mypenavigatorapi.auth.domain.dto.GoogleAuthenticationLogin;
import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.domain.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
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
        Optional<User> user = usersRepository.findByEmail(request.getUsername());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        if (!user.get().isEnabled()) {
            throw new UsernameNotFoundException("El usuario no está activo");
        }

        if (!user.get().isGoogleAccount()) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }

        String token = jwtService.generateToken(user.get());

        return new AuthenticationResponse(
                token,
                Mapper.map(user.get(), UserDto.class)
        );
    }

    public AuthenticationResponse loginWithGoogle(GoogleAuthenticationLogin googleLogin){
        FirebaseToken decodedToken = null;

        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(googleLogin.getIdToken());
        } catch (FirebaseAuthException e) {
            throw new BadRequestException("Token de Google inválido");
        }
        String email = decodedToken.getEmail();

        Optional<User> userOpt = usersRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        User user = userOpt.get();

        if (!user.isGoogleAccount()) {
            throw new BadRequestException("El usuario no es una cuenta de Google");
        }

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, Mapper.map(user, UserDto.class));
    }
}
