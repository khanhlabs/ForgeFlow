package com.example.backend.auth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.auth")
@Getter
@Setter
public class AuthProperties {

    private long refreshTokenExpirationDays = 7;
}
