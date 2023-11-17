package com.example.myresale.controllers;

import com.example.myresale.DTOs.UserInfoDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.UserInfoDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserInfoDetailsService service;

    public RegistrationController(UserInfoDetailsService service) {
        this.service = service;
    }

    @GetMapping
    public String registerForm(){
        return "registrationPage.html";
    }

    @PostMapping
    public ResponseEntity<String> processRegistration(UserInfoDTO userInfoDTO){
        UserInfo userInfo = service.saveUserInfo(userInfoDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Successfully create user '" + userInfo.getUsername() + "'");

    }
}
