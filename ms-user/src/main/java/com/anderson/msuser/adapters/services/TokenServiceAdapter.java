package com.anderson.msuser.adapters.services;

import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.services.TokenService;
import com.anderson.msuser.shared.exceptions.UnexpectedException;
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

import static com.anderson.msuser.shared.constants.ExceptionConstants.ERROR_GENERATING_TOKEN;
import static com.anderson.msuser.shared.constants.ExceptionConstants.TOKEN_VALIDATION_ERROR;

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
                    .withExpiresAt(expirationDate())
                    .sign(createAlgorithm());
        } catch (JWTCreationException e) {
            throw new UnexpectedException(ERROR_GENERATING_TOKEN);
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
            throw new UnexpectedException(TOKEN_VALIDATION_ERROR);
        }
    }

    private Algorithm createAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
