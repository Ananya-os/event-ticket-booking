package com.ananya.event_ticket_booking.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ananya.event_ticket_booking.dto.LoginRequest;

import jakarta.validation.Valid;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {

        System.out.println("Inside login controller");

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            return "Login Successful";

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}