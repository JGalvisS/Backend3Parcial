package com.example.apigatewayservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                /*.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/public/**").permitAll() // Permitir acceso público a las rutas /public/**
                        .anyExchange().authenticated() // Todas las demás rutas requieren autenticación
                )*/
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2Login(Customizer.withDefaults()) ;// Configurar OAuth2 login con configuraciones por defecto;

        return http.build();
    }
}

