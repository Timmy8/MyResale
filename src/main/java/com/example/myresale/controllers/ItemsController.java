package com.example.myresale.controllers;

import com.example.myresale.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "itemsPage.html";
    }

    @GetMapping("{id}")
    public String findItemById(@PathVariable("id") String id, Model model){
        model.addAttribute("item", itemService.findItemById(Long.parseLong(id)));
        return "item.html";
    }
}
