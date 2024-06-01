package com.elaparato.elaparato.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyCloakJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    //
    private final ObjectMapper objectMapper;

    public KeyCloakJwtAuthenticationConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    //

    private Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) throws JsonProcessingException {
        Set<GrantedAuthority> resourcesRoles = new HashSet<>();
        //ObjectMapper objectMapper = new ObjectMapper();
        JsonNode claims = objectMapper.readTree(objectMapper.writeValueAsString(jwt.getClaims()));

        resourcesRoles.addAll(extractRoles("resource_access", claims));
        return resourcesRoles;
    }

    private static Collection<? extends GrantedAuthority> extractRoles(String route, JsonNode jwt) {
        Set<String> rolesWithPrefix = new HashSet<>();

        jwt.path(route)
                .elements()
                .forEachRemaining(e -> e.path("roles")
                        .elements()
                        .forEachRemaining(r -> rolesWithPrefix.add("ROLE_" + r.asText())));

        return AuthorityUtils.createAuthorityList(rolesWithPrefix.toArray(new String[0]));
    }

    @Override
    public Collection<GrantedAuthority> convert(final Jwt source) {
        Collection<GrantedAuthority> authorities;
        try {
            authorities = this.getGrantedAuthorities(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error extracting roles from JWT", e);
        }
        return authorities;
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(Jwt source) throws JsonProcessingException {
        return Stream.concat(
                defaultGrantedAuthoritiesConverter.convert(source).stream(),
                extractResourceRoles(source).stream()
        ).collect(Collectors.toSet());
    }
}


