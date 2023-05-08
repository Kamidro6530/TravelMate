package com.example.services.user;

import com.example.model.User;
import com.example.services.CrudService;

public interface UserService extends CrudService<User,Long> {

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    User findUserByLastname(String lastname);

}
