package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String registration(){
        return "authorization/register";
    }

    @GetMapping("/login")
    public String login(){
        return "authorization/login";
    }
}
