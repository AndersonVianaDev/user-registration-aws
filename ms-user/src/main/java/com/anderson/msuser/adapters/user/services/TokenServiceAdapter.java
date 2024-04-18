package com.anderson.msuser.adapters.user.services;

import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.services.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenServiceAdapter implements TokenService {

    @Value("${key-secret}")
    private String secret;

    @Override
    public String generator(User user) {
        try {
            return JWT.create()
                    .withIssuer("auth")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(exirationDate())
                    .sign(createAlgorithm());
        } catch (JWTCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UUID validateToken(String token) {
        try {
            return UUID.fromString(
                    JWT.require(createAlgorithm())
                            .withIssuer("auth")
                            .build()
                            .verify(token)
                            .getSubject()
            );
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Algorithm createAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant exirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
