package com.smart_ski_rent_ver1_2.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

    @Slf4j
    @RestController
    @RequestMapping("/api/auth")
    public class LoginByFrontendController {

        private final AuthenticationManager authenticationManager;

        public LoginByFrontendController(AuthenticationManager authenticationManager) {
            this.authenticationManager = authenticationManager;
        }


        @GetMapping("/login")
        public String login() {
            return "login";
        }
        @PostMapping("/login")
        public ResponseEntity<?> authenticateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
            String[] credentials = decodeBasicAuthHeader(authHeader);
            String username = credentials[0];
            String password = credentials[1];

            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                );

                // Użytkownik uwierzytelniony
                log.info("User {} authenticated successfully", username);
                return ResponseEntity.ok("User authenticated successfully");

            } catch (AuthenticationException e) {
                log.error("Authentication failed for user {}: {}", username, e.getMessage());
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        }

        private String[] decodeBasicAuthHeader(String authHeader) {
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                String base64Credentials = authHeader.substring("Basic ".length()).trim();
                String credentials = new String(Base64.getDecoder().decode(base64Credentials));
                return credentials.split(":", 2); // Zwracamy nazwę użytkownika i hasło
            }
            throw new IllegalArgumentException("Invalid Authorization Header");
        }

    }

