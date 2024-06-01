package com.elaparato.elaparato.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String issuerUri;

    @Bean
    public JwtDecoder jwtDecoder() {
        // Configura el JwtDecoder con el valor de issuer-uri inyectado desde application.yml
        return NimbusJwtDecoder
                .withJwkSetUri(issuerUri)
                .build();
    }
}

