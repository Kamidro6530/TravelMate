package com.example.services;

import com.example.model.User;

import java.util.Set;

public interface UserService {

    User registerNewUserAccount(User user);
    User save(User object);

    User findById(Long id);

    Set<User> findAll();

    void deleteById(Long id);

    void delete(User object);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    User findUserByLastname(String lastname);

}
