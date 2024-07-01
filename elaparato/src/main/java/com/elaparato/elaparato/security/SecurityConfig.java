package com.elaparato.elaparato.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakJwtAuthenticationConverter());

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,"/productos/**").hasAnyRole("repositor","admin")
                        .requestMatchers(HttpMethod.POST,"/productos/**").hasAnyRole("repositor","admin")
                        .requestMatchers(HttpMethod.PUT,"/productos/**").hasAnyRole("repositor","admin")

                        .requestMatchers(HttpMethod.GET,"/ventas/**").hasAnyRole("admin","vendedor")
                        .requestMatchers(HttpMethod.POST,"/ventas/**").hasAnyRole("admin","vendedor")
                        .requestMatchers(HttpMethod.PUT,"/ventas/**").hasAnyRole("admin","vendedor")

                        .requestMatchers(HttpMethod.GET,"/users/**").hasRole("admin")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter)
                );

        return http.build();
    }

}
