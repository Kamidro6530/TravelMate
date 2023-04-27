package com.example.services;

import com.example.model.User;
import com.example.model.dto.UserDto;

import java.util.Set;

public interface UserService {
    User save(User object);

    User findById(Long id);

    Set<User> findAll();

    void deleteById(Long id);

    void delete(User object);

    UserDto findUserByEmail(String email);

    UserDto findUserByUsername(String username);

    UserDto findUserByLastname(String lastname);

}
