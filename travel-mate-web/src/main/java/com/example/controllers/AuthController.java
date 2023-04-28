package com.example.controllers;

import com.example.model.User;
import com.example.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

   private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String registrationForm (Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "authorization/register";
    }

    @PostMapping("register/save")
    public String registrationNewUser(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "error";
        }else {
            User savedUser = userService.save(user);
            return "redirect:/index";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "authorization/login";
    }
}
