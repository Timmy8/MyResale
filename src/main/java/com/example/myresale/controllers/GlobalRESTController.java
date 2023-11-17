package com.example.myresale.controllers;

import com.example.myresale.DTOs.AddDeliveryAddressDTO;
import com.example.myresale.DTOs.DeleteItemRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.DTOs.CreateItemRequestDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class GlobalRESTController {
    private ItemService itemService;

    public GlobalRESTController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items/delete")
    public ResponseEntity<String> deleteItem(@ModelAttribute("deleteItemDTO") @Valid DeleteItemRequestDTO dto){
        Item item = itemService.deleteItem(dto.getId());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Item: " + item + " deleted with reason: \"" + dto.getReason() +"\"");
    }
}

