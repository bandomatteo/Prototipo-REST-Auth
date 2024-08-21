package com.bandomatteo.Prototipo_REST_Auth.services;

import com.bandomatteo.Prototipo_REST_Auth.domain.dto.AuthenticationRequestDTO;
import com.bandomatteo.Prototipo_REST_Auth.domain.dto.AuthenticationResponseDTO;
import com.bandomatteo.Prototipo_REST_Auth.domain.dto.RegisterRequestDTO;
import com.bandomatteo.Prototipo_REST_Auth.domain.entities.User.Role;
import com.bandomatteo.Prototipo_REST_Auth.domain.entities.User.UserEntity;
import com.bandomatteo.Prototipo_REST_Auth.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private  JWTService jwtService;

    private AuthenticationManager authenticationManager;



    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JWTService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponseDTO register(RegisterRequestDTO request) {

        UserEntity user = UserEntity
                .builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

         var jwt = jwtService.generateToken(user);

         return AuthenticationResponseDTO
                 .builder()
                 .token(jwt)
                 .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserEntity user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        var jwt = jwtService.generateToken(user);

        return AuthenticationResponseDTO
                .builder()
                .token(jwt)
                .build();
    }
}
