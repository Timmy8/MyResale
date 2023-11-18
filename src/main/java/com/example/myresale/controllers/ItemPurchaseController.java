package com.example.myresale.controllers;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.services.ItemService;
import com.example.myresale.services.UserCartService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase")
public class ItemPurchaseController {
    private ItemService itemService;

    public ItemPurchaseController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public String itemPurchaseForm(@PathVariable("id") int id,
                                   Model model){

        model.addAttribute("itemId", id);

        return "form_item_purchase.html";
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> purchaseItem(@PathVariable("id") Long id,
                                               @ModelAttribute("deliveryAddress") DeliveryAddressCreateDTO dto,
                                               Model model){

        if (itemService.isAvailable(id)) {
            itemService.setItemAvailable(false, id);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Thank you for your purchase");
        } else {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Sorry, item is unavailable");
        }
    }
}
