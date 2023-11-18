package com.example.myresale.controllers;

import com.example.myresale.services.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GlobalRESTController {
    private ItemService itemService;

    public GlobalRESTController(ItemService itemService) {
        this.itemService = itemService;
    }

}

