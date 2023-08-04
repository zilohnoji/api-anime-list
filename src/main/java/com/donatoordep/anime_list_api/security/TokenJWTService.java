package com.donatoordep.anime_list_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.donatoordep.anime_list_api.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenJWTService {

    private final String SECRET = "super-key-protect";

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withIssuer("anime-api")
                .withSubject(user.getEmail())
                .withExpiresAt(generateExpirationDateToken())
                .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.require(algorithm)
                .withIssuer("anime-api")
                .build()
                .verify(token)
                .getSubject();
    }

    public Instant generateExpirationDateToken() {
        return LocalDateTime.now().plusHours(168).toInstant(ZoneOffset.of("-03:00"));
    }
}
