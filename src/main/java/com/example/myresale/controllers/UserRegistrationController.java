package com.example.myresale.controllers;

import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.components.UserRoleEnum;
import com.example.myresale.exceptions.UserExistsException;
import com.example.myresale.services.UserInfoDetailsService;
import com.example.myresale.services.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private final UserInfoDetailsService userInfoService;
    private final Logger logger = Logger.getLogger(UserRegistrationController.class.getName());

    public UserRegistrationController(UserInfoDetailsService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping
    public String registerForm(){
        return "page_registration.html";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("UserInfoDTO") @Valid UserInfoCreateDTO userInfoDTO, Errors errors, Model model){
        if (errors.hasErrors()) {
            List<String> errorsList = new ArrayList<>();

            errors.getAllErrors().forEach(error -> errorsList.add(error.getDefaultMessage()));
            logger.info(errorsList.toString());
            model.addAttribute("errors", errorsList);

            return "page_registration.html";
        } else {
            try {
                var userInfo = userInfoService.saveUserInfo(userInfoDTO);
            } catch (UserExistsException ex){
                model.addAttribute("errors", ex.getMessage());
                return "page_registration.html";
            }

            return "redirect:/login?registered=ok";
        }
    }
}
