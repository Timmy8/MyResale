package com.example.myresale.controllers;

import com.example.myresale.services.UserInfoDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    UserInfoDetailsService service;

    public LoginController(UserInfoDetailsService service) {
        this.service = service;
    }

    @GetMapping
    public String loginPage(){
        return "loginPage.html";
    }
}
