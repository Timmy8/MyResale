package com.example.myresale.controllers;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.DeliveryAddressService;
import com.example.myresale.services.ItemService;
import com.example.myresale.services.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/purchase")
public class ItemPurchaseController {
    private final ItemService itemService;
    private final PurchaseOrderService purchaseOrderService;

    public ItemPurchaseController(ItemService itemService, PurchaseOrderService purchaseOrderService) {
        this.itemService = itemService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/{id}")
    public String itemPurchaseForm(@PathVariable("id") int id,
                                   Model model)
    {

        model.addAttribute("itemId", id);

        return "form_item_purchase.html";
    }

    @GetMapping("/allCartPurchase")
    public String purchaseAllFromUserCart(Model model){

        model.addAttribute("itemId", "allCartPurchase");

        return "form_item_purchase.html";
    }

    @PostMapping("/{id:\\d+}")
    public ResponseEntity<String> purchaseItem(@PathVariable("id") Long itemId,
                                               @ModelAttribute("deliveryAddress") DeliveryAddressCreateDTO dto,
                                               Authentication authentication) {
        UserInfo user = null;
        if (authentication != null) {
            user = (UserInfo) authentication.getPrincipal();
        }

        var item = itemService.findItemById(itemId);
        var orderId = purchaseOrderService.saveOrder(Set.of(item), dto, user);

        return orderId.map(id -> ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Thank you for your purchase\nPurchase number - " + id)).orElseGet(() -> ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Sorry, item is unavailable"));
    }

    @PostMapping("/allCartPurchase")
    public ResponseEntity<String> purchaseAllCart(@ModelAttribute("deliveryAddress") DeliveryAddressCreateDTO dto,
                                                  Authentication authentication)
    {
        var user = (UserInfo) authentication.getPrincipal();

        var orderId = purchaseOrderService.saveOrder(user.getUserCart().getAllItemsFromCart(), dto, user);

        return orderId.map(id -> ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Thank you for your purchase\nPurchase number - " + id)).orElseGet(() -> ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Sorry, item is unavailable"));
    }
}
