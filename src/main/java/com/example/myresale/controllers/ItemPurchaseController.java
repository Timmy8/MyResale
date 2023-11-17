package com.example.myresale.controllers;

import com.example.myresale.DTOs.AddDeliveryAddressDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase")
public class ItemPurchaseController {

    @GetMapping("/{id}")
    public String itemPurchaseForm(@PathVariable("id") int id,
                                   Model model){
        model.addAttribute("itemId", id);

        return "form_item_purchase.html";
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> purchaseItem(@PathVariable("id") int id,
                                               @ModelAttribute("deliveryAddress") AddDeliveryAddressDTO dto,
                                               Model model){



        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Thank you for your purchase");
    }
}
