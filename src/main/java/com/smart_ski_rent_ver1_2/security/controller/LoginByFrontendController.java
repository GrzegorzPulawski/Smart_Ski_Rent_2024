package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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


        @PostMapping("/login")
        public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
            log.info("Attempting to log in user: {}", loginRequest.getUsername());
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("User {} authenticated successfully", username);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                return ResponseEntity.ok("User authenticated successfully");

            } catch (AuthenticationException e) {
                log.error("Authentication failed for user {}: {}", username, e.getMessage());
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        }


    }

