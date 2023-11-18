package com.example.myresale.controllers;

import com.example.myresale.DTOs.ItemCreateRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/create")
public class ItemCreateController {
    private ItemService itemService;

    public ItemCreateController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String itemCreateForm(){
        return "form_item_creation.html";
    }

    @PostMapping
    public String createItem(@ModelAttribute("createItemDTO") @Valid ItemCreateRequestDTO item,
                             Errors errors,Authentication user, Model model){

        if (errors.hasErrors()) {
            List<String> errorsList = new ArrayList<>();

            errors.getAllErrors().forEach(error -> errorsList.add(error.getDefaultMessage()));
            model.addAttribute("errors", errorsList);

            return "form_item_creation.html";
        } else {

            UserInfo userInfo = (UserInfo)user.getPrincipal();
            item.setCreatedBy(userInfo);

            Item createdItem = itemService.addItem(item);

            return "redirect:/items/" + createdItem.getId();
        }

    }
}
