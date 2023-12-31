package com.donatoordep.anime_list_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.donatoordep.anime_list_api.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenJWTService {

    @Value("${key.secret}")
    private String SECRET;

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("anime-api")
                .withSubject(user.getEmail())
                .withExpiresAt(generateExpirationDateToken())
                .sign(algorithm());
    }

    public String validateToken(String token) {
        return JWT.require(algorithm())
                .withIssuer("anime-api")
                .build()
                .verify(token)
                .getSubject();
    }

    public Instant generateExpirationDateToken() {
        return LocalDateTime.now().plusHours(168).toInstant(ZoneOffset.of("-03:00"));
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(SECRET);
    }
}
