package com.example.myresale.controllers;

import com.example.myresale.DTOs.CreateItemRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create")
public class CreateController {
    private ItemService itemService;

    public CreateController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public String createItem(@ModelAttribute("createItemDTO") @Valid CreateItemRequestDTO item, Authentication user){
        UserInfo userInfo = (UserInfo)user.getPrincipal();
        item.setCreatedBy(userInfo);

        Item createdItem = itemService.addItem(item);

        return "redirect:/items/" + createdItem.getId();
    }
}
