package com.smart_ski_rent_ver1_2.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.smart_ski_rent_ver1_2.security.dto.UserDto;
import com.smart_ski_rent_ver1_2.security.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-value}")
    private String secretKey;
    private final UserServiceNew userServiceNew;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);

        UserDto user = userServiceNew.findByLogin(login);

        return JWT.create()
                .withIssuer(login)
                .withClaim("userId", String.valueOf(user.getId()))
                .withClaim("firstName", user.getFirstName())  // Dodanie firstName
                .withClaim("lastName", user.getLastName())    // Dodanie lastName
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                    .build();
            DecodedJWT decoded = verifier.verify(token);

            UserDto user = userServiceNew.findByLogin(decoded.getIssuer());

            return new
                    UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (
                JWTVerificationException exception) {
            // Obsługa wyjątku, gdy token jest nieprawidłowy
            throw new RuntimeException("Invalid JWT token", exception);
        }
    }
    public Long extractUserId(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                    .build();
            DecodedJWT decoded = verifier.verify(token);

            // Pobierz `userId` z payloadu tokenu (załóżmy, że token zawiera `userId` jako klucz w payload)
            String userIdString = decoded.getClaim("userId").asString();

            if (userIdString != null) {
                return Long.valueOf(userIdString);
            } else {
                throw new RuntimeException("Token does not contain userId");
            }
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid JWT token", exception);
        }
    }

}