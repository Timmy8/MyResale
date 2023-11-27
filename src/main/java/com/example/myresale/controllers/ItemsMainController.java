package com.example.myresale.controllers;

import com.example.myresale.entities.Item;
import com.example.myresale.services.ItemService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsMainController {
    private final ItemService itemService;

    public ItemsMainController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String findAllItems(@RequestParam(value = "keyword", required = false) String keyword, Model model){
        List<Item> itemsList;

        if (keyword != null)
            itemsList = itemService.findAllItemsByKeyword(keyword);
        else
            itemsList = itemService.findAllItems();

        model.addAttribute("items", itemsList);
        return "page_items_main.html";
    }


    @GetMapping("/{id:\\d+}")
    public String findItemById(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", itemService.findItemById(id));

        return "page_item_info.html";
    }
}
