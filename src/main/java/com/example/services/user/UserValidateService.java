package com.example.services.user;

import com.example.model.User;
import org.springframework.validation.Errors;

public interface UserValidateService {

    String validatePasswords(User user, Errors errors);

    String validateEmailExist(User user, Errors errors);
}
