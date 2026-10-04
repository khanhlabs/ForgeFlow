package com.example.backend.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("seed-users")
public class UserSeedRunner implements CommandLineRunner {

    private final UserSeedUtils userSeedUtils;
    private final String rawPassword;

    public UserSeedRunner(UserSeedUtils userSeedUtils, @Value("${SEED_USERS_PASSWORD}") String rawPassword) {
        this.userSeedUtils = userSeedUtils;
        this.rawPassword = rawPassword;
    }

    @Override
    public void run(String... args) {
        userSeedUtils.insert25Users(rawPassword);
    }
}
