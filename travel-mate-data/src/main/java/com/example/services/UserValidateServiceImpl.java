package com.example.services;

import com.example.model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class UserValidateServiceImpl implements UserValidateService{

    private final UserService userService;

    public UserValidateServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validatePasswords(User user, Errors errors) {
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
            }

    }

    @Override
    public void validateEmailExist(User user, Errors errors) {
            if (userService.findUserByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "email.alreadyExist",
                        ("There is an account with that email address: " + user.getEmail()));
            }
    }
}
