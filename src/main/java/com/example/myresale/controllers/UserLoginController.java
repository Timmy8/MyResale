package com.example.myresale.controllers;

import com.example.myresale.services.UserInfoDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserLoginController {
    UserInfoDetailsService service;

    public UserLoginController(UserInfoDetailsService service) {
        this.service = service;
    }

    @GetMapping
    public String loginPage(){
        return "page_login.html";
    }
}
