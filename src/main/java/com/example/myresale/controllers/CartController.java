package com.example.myresale.controllers;

import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.UserCartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {
    private UserCartService service;

    public CartController(UserCartService service) {
        this.service = service;
    }

    @GetMapping
    public String cartView(Model model, Authentication auth){
        var user = (UserInfo)auth.getPrincipal();
        model.addAttribute("items", service.getAllItemsFromUserCart(authenticationToUserId(auth)));

        return "userCartPage.html";
    }

    @PostMapping
    public String addItem(Authentication auth, @RequestParam("id") String id){
        service.addItemToUserCart(authenticationToUserId(auth), Long.parseLong(id));

        return "redirect:/items";
    }

    @GetMapping("/clear")
    public String clearCart(Authentication auth){
        service.clearUserCart(authenticationToUserId(auth));

        return "redirect:/cart";
    }

    @GetMapping("/delete")
    public String deleteItem(Authentication auth, @RequestParam("id") String id){
        service.deleteItemFromUserCart(authenticationToUserId(auth), Long.parseLong(id));

        return "redirect:/cart";
    }

    private static Long authenticationToUserId(Authentication auth){
        UserInfo user = (UserInfo)auth.getPrincipal();
        return user.getId();
    }
}
