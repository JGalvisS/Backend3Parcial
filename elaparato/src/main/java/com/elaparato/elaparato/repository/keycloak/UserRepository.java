package com.elaparato.elaparato.repository.keycloak;

import com.elaparato.elaparato.model.User;
import lombok.RequiredArgsConstructor;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
    private final Keycloak keycloakClient;

    //@Value("${dh.keycloack.realm}")
    private String realm= "elAparatoGalvis";


    @Override
    public List<User> findAll() {
        return keycloakClient.realm(realm).users().list().stream()
                .map(this::toUser).collect(Collectors.toList());
    }

    private User toUser(UserRepresentation userRepresentation){
        return User.builder()
                .id(userRepresentation.getId())
                .userName(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .build();
    }

    @Override
    public List<User> findByUserName(String userName) {
        List<UserRepresentation> userRepresentationslist = keycloakClient
                .realm(realm)
                .users()
                .search(userName);
        return userRepresentationslist.stream().map(this::toUser)
                .collect(Collectors.toList());

    }
}
