package com.example.controllers;

import com.example.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/register")
    public String registration(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "authorization/register";
    }

    @RequestMapping("/login")
    public String login(){
        return "authorization/login";
    }
}
