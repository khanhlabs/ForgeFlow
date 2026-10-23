package com.example.backend.auth.services;

import com.example.backend.auth.properties.AuthProperties;
import com.example.backend.users.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private final AuthProperties authProperties;
    private final SecretKey signingKey;

    public JwtService(
            AuthProperties authProperties
    ) {
        this.authProperties = authProperties;

        byte[] keyBytes = Decoders.BASE64.decode(
                authProperties.getJwtSecret()
        );

        if (keyBytes.length < 32) {
            throw new IllegalStateException(
                    "JWT secret must contain at least 32 bytes"
            );
        }

        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user){
        Instant now = Instant.now();
        Instant expiration = now.
                plus(authProperties.getAccessTokenExpirationMinutes(), ChronoUnit.MINUTES);

        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(signingKey)
                .compact();
    }

    public Claims validateAndExtractClaims(String token){
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
