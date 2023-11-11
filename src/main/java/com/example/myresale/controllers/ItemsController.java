package com.example.myresale.controllers;

import com.example.myresale.entities.DTOs.DeleteItemRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.DTOs.CreateItemRequestDTO;
import com.example.myresale.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String findAllItems(Model model){
        model.addAttribute("items", itemService.findAllItems());
        return "items.html";
    }

    @GetMapping("{id}")
    public String findItemById(@PathVariable("id") String id, Model model){
        model.addAttribute("item", itemService.findItemById(Long.parseLong(id)));
        return "item.html";
    }
}
