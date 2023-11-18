package com.example.myresale.controllers;

import com.example.myresale.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemsMainController {
    private ItemService itemService;

    public ItemsMainController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String findAllItems(Model model){
        model.addAttribute("items", itemService.findAllItems());
        return "page_items_main.html";
    }

    @GetMapping("/{id:\\d+}")
    public String findItemById(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", itemService.findItemById(id));

        return "page_item_info.html";
    }
}
