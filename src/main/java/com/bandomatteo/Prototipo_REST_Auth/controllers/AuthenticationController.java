package com.bandomatteo.Prototipo_REST_Auth.controllers;

import com.bandomatteo.Prototipo_REST_Auth.domain.dto.AuthenticationRequestDTO;
import com.bandomatteo.Prototipo_REST_Auth.domain.dto.AuthenticationResponseDTO;
import com.bandomatteo.Prototipo_REST_Auth.domain.dto.RegisterRequestDTO;
import com.bandomatteo.Prototipo_REST_Auth.services.AuthenticationService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.System.*;

@RestController
@RequestMapping( "/api/v1/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        //System.out.println(request);
        return ResponseEntity.ok(authenticationService.register(request));
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
