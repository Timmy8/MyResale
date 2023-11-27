package com.example.myresale.controllers;

import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.PurchaseOrderService;
import com.example.myresale.services.UserCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class UserCartController {
    private final UserCartService userCartService;
    private final PurchaseOrderService purchaseOrderService;

    public UserCartController(UserCartService userCartService, PurchaseOrderService purchaseOrderService) {
        this.userCartService = userCartService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping
    public String cartView(Model model, Authentication auth){
        var user = (UserInfo)auth.getPrincipal();
        var itemsList = userCartService.getAllItemsFromUserCart(authenticationToUserId(auth));
        BigDecimal priceSum = BigDecimal.ZERO;

        for (var item : itemsList){
            if (item.isAvailable())
                priceSum = priceSum.add(item.getPrice());
        }

        model.addAttribute("items", itemsList);
        model.addAttribute("fullPrice", priceSum);

        return "page_user_cart.html";
    }

    @PostMapping
    public String addItem(Authentication auth, @RequestParam("id") String id){
        userCartService.addItemToUserCart(authenticationToUserId(auth), Long.parseLong(id));

        return "redirect:/items";
    }

    @GetMapping("/clear")
    public String clearCart(Authentication auth){
        userCartService.clearUserCart(authenticationToUserId(auth));

        return "redirect:/cart";
    }

    @GetMapping("/delete")
    public String deleteItem(Authentication auth, @RequestParam("id") String id){
        userCartService.deleteItemFromUserCart(authenticationToUserId(auth), Long.parseLong(id));

        return "redirect:/cart";
    }

    @GetMapping("/purchases")
    public String userPurchases(Authentication auth, Model model){
        var userPurchsesList = purchaseOrderService.findAllOrdersByUserId(authenticationToUserId(auth));

        model.addAttribute("purchases", userPurchsesList);

        return "page_user_purchases.html";
    }

    private static Long authenticationToUserId(Authentication auth){
        UserInfo user = (UserInfo)auth.getPrincipal();
        return user.getId();
    }
}
