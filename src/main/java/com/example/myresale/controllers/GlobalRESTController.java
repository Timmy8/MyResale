package com.example.myresale.controllers;

import com.example.myresale.entities.DTOs.DeleteItemRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.DTOs.CreateItemRequestDTO;
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

    @PostMapping("/items/create")
    public ResponseEntity<String> createItem(@ModelAttribute("createItemDTO") @Valid CreateItemRequestDTO item, Authentication user){
        String url = "http://localhost:8080/items/";
        UserInfo userInfo = (UserInfo)user.getPrincipal();
        item.setCreatedBy(userInfo);

        Item createdItem = itemService.addItem(item);
        URI uri = URI.create(url + createdItem.getId());

        return ResponseEntity
                .created(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Successfully create item: " + createdItem + "\nLink: " + uri);
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

