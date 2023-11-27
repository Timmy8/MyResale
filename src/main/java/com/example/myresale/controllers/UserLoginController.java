package com.example.myresale.controllers;

import com.example.myresale.services.UserInfoDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class UserLoginController {
    @GetMapping
    public String loginPage(@RequestParam(value = "registered", required = false) String registered, Model model){

        if (registered != null && registered.equals("ok"))
            model.addAttribute("message", "Successfully registered!");

        return "page_login.html";
    }
}
