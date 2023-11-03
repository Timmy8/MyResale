package com.example.myresale.controllers;

import com.example.myresale.entities.Item;
import com.example.myresale.services.InteractionWithItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsControllers {
    private InteractionWithItemService itemService;

    public ItemsControllers(InteractionWithItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> findAllItems(){
        return itemService.findAllItems();
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> findItemById(@PathVariable("id") String id){
        return ResponseEntity
                .ok()
                .body(itemService.findItemById(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item item){
        Item createdItem = itemService.addItem(item);
        long createdItemId = createdItem.getId();
        URI uri = URI.create("http://localhost:8080/" + createdItemId);

        return ResponseEntity
                .created(uri)
                .body(createdItem);
    }

}
