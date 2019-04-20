package com.authenticationservice.configuration.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties related with JWT token
 */
@Getter
@Configuration
public class JwtConfiguration {

    @Value("${security.jwt.token.authorizationPrefix}")
    private String authorizationPrefix;

    @Value("${security.jwt.token.secretKey}")
    private String secretKey;

    @Value("${security.jwt.token.expirationTimeInMilliseconds}")
    private long expirationTimeInMilliseconds;

}
