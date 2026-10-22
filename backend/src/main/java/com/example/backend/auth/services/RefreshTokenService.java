package com.example.backend.auth.services;

import com.example.backend.auth.entities.RefreshToken;
import com.example.backend.auth.repositories.RefreshTokenRepository;
import com.example.backend.users.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenGenerator generator;
    private final long refreshTokenExpirationDays;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            RefreshTokenGenerator generator,
            @Value("${refresh-token-expiration-days: 7}")
            long refreshTokenExpirationDays
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.generator = generator;
        this.refreshTokenExpirationDays = refreshTokenExpirationDays;
    }

    @Transactional
    public String createRefreshToken(User user) {
        String rawToken = generator.generate();

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setTokenHash(generator.hash(rawToken));
        refreshToken.setFamilyId(UUID.randomUUID());
        refreshToken.setExpiresAt(
                Instant.now().plus(refreshTokenExpirationDays, ChronoUnit.DAYS)
        );

        refreshTokenRepository.save(refreshToken);
        return rawToken;
    }
}
