package com.example.controllers;

import com.example.model.User;
import com.example.services.UserService;
import com.example.services.UserValidateService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

   private final UserService userService;
   private final UserValidateService userValidateService;

    public AuthController(UserService userService, UserValidateService userValidateService) {
        this.userService = userService;
        this.userValidateService = userValidateService;
    }

    @RequestMapping("/register")
    public String registrationForm (Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "authorization/register";
    }

    @PostMapping("register/save")
    public String registrationNewUser(@Valid User user, BindingResult result){
        userValidateService.validateEmailExist(user,result);
        userValidateService.validatePasswords(user,result);
        if (result.hasErrors()){
            return "authorization/register";
        }else {
            userService.save(user);
            return "redirect:/index";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "authorization/login";
    }
}
