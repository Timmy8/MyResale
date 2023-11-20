package com.example.myresale.controllers;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.entities.DeliveryAddress;
import com.example.myresale.entities.PurchaseOrder;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.repositories.DeliveryAddressRepository;
import com.example.myresale.services.DeliveryAddressService;
import com.example.myresale.services.ItemService;
import com.example.myresale.services.PurchaseOrderService;
import com.example.myresale.services.UserCartService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@Controller
@RequestMapping("/purchase")
public class ItemPurchaseController {
    private ItemService itemService;
    private PurchaseOrderService purchaseOrderService;
    private DeliveryAddressService deliveryAddressService;

    public ItemPurchaseController(ItemService itemService, PurchaseOrderService purchaseOrderService, DeliveryAddressService deliveryAddressService) {
        this.itemService = itemService;
        this.purchaseOrderService = purchaseOrderService;
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping("/{id}")
    public String itemPurchaseForm(@PathVariable("id") int id,
                                   Model model){

        model.addAttribute("itemId", id);

        return "form_item_purchase.html";
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> purchaseItem(@PathVariable("id") Long itemId,
                                               @ModelAttribute("deliveryAddress") DeliveryAddressCreateDTO dto,
                                               Authentication authentication){

        if (itemService.isAvailable(itemId)) {
            UserInfo user = null;
            if (authentication != null) {
                user = (UserInfo) authentication.getPrincipal();
            }

            Long orderId = purchaseOrderService.saveOrder(itemId, dto, user);

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Thank you for your purchase\nPurchase number - " + orderId);
        } else {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Sorry, item is unavailable");
        }
    }
}
