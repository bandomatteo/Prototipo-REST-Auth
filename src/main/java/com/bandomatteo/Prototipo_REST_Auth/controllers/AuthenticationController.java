package com.bandomatteo.Prototipo_REST_Auth.controllers;

import com.bandomatteo.Prototipo_REST_Auth.domain.dto.AuthenticationRequestDTO;
import com.bandomatteo.Prototipo_REST_Auth.domain.dto.AuthenticationResponseDTO;
import com.bandomatteo.Prototipo_REST_Auth.domain.dto.RegisterRequestDTO;
import com.bandomatteo.Prototipo_REST_Auth.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping(name = "/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(name = "/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
