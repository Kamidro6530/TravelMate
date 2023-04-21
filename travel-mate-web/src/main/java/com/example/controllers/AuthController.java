package com.example.controllers;

import com.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String registration(Model model){
        model.addAttribute("user", User.builder().build());
        return "authorization/register";
    }

    @GetMapping("/login")
    public String login(){
        return "authorization/login";
    }
}
