package com.bandomatteo.Prototipo_REST_Auth.config;


import com.bandomatteo.Prototipo_REST_Auth.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final com.bandomatteo.Prototipo_REST_Auth.services.JWTService JWTService;

    public JWTAuthenticationFilter(JWTService JWTService) {
        this.JWTService = JWTService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain )
            throws ServletException, IOException {


        final String auth = request.getHeader("Authorization");
        final String token;
        final String userEmail;

        if (auth != null && auth.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = auth.substring(7);
        userEmail = JWTService.extractUsername(token) ; // TODO: extract the email from JTW token



    }
}
