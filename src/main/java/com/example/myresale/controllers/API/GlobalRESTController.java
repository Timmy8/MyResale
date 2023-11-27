package com.example.myresale.controllers.API;

import com.example.myresale.entities.Item;
import com.example.myresale.services.ItemService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GlobalRESTController {
    private final ItemService itemService;

    public GlobalRESTController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/findAllItems")
    public ResponseEntity<List<Item>> findAllItems(@RequestParam(value = "keyword", required = false) String keyword){
        List<Item> itemsList;

        if (keyword != null)
            itemsList = itemService.findAllItemsByKeyword(keyword);
        else
            itemsList = itemService.findAllItems();

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemsList);
    }

    @GetMapping("/findItemById/{id:\\d+}")
    public ResponseEntity<Item> findItemById(@PathVariable("id") Long id){

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemService.findItemById(id));
    }

    @GetMapping("/isAvailable/{id:\\d+}")
    public ResponseEntity<Boolean> itemAvailableById(@PathVariable("id") Long id){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemService.isAvailable(id));
    }

}

