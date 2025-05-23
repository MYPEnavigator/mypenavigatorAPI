package com.example.mypenavigatorapi.auth.services;

import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontradon con correo: " + email));

        if (!user.isEnabled()) {
            throw new DisabledException("El usuario no está activo");
        }

        return user;
    }

}
