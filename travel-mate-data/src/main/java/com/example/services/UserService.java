package com.example.services;

import com.example.model.dto.UserDto;

public interface UserService extends CrudService<UserDto,Long>{

    UserDto findUserByEmail(String email);

    UserDto findUserByUsername(String username);

    UserDto findUserByLastname(String lastname);

}
