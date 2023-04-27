package com.example.controllers;

import com.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/register")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "authorization/register";
    }

    @RequestMapping("/login")
    public String login(){
        return "authorization/login";
    }
}
