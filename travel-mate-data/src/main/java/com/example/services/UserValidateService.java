package com.example.services;

import com.example.model.User;
import org.springframework.validation.Errors;

public interface UserValidateService {

    void validatePasswords(User user, Errors errors);

    void validateEmailExist(User user, Errors errors);
}
