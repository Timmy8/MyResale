package com.example.myresale.controllers;

import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.UserInfoDetailsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private final UserInfoDetailsService service;
    private final Logger logger = Logger.getLogger(UserRegistrationController.class.getName());

    public UserRegistrationController(UserInfoDetailsService service) {
        this.service = service;
    }

    @GetMapping
    public String registerForm(){
        return "page_registration.html";
    }

    @PostMapping
    public String processRegistration( @ModelAttribute("UserInfoDTO") UserInfoCreateDTO userInfoDTO, Errors errors, Model model){
        if (errors.hasErrors()) {
            List<String> errorsList = new ArrayList<>();

            errors.getAllErrors().forEach(error -> errorsList.add(error.getDefaultMessage()));
            logger.info(errorsList.toString());
            model.addAttribute("errors", errorsList);

            return "page_registration.html";
        } else {

            UserInfo userInfo = service.saveUserInfo(userInfoDTO);
            model.addAttribute("message", "Registration completed successfully!");

            return "redirect:/login";
        }
    }
}
