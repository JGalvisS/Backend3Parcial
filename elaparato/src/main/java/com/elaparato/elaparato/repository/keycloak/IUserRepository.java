package com.elaparato.elaparato.repository.keycloak;

import com.elaparato.elaparato.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    List<User> findByUserName(String userName);
}
