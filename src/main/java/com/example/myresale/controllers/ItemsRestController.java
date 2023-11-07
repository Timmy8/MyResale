package com.example.myresale.controllers;

import com.example.myresale.entities.DTOs.DeleteItemRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.DTOs.CreateItemRequestDTO;
import com.example.myresale.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsRestController {
    private ItemService itemService;

    public ItemsRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> findAllItems(){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemService.findAllItems());
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> findItemById(@PathVariable("id") String id){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemService.findItemById(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody @Valid CreateItemRequestDTO item){
        String url = "http://localhost:8080/";
        Item createdItem = itemService.addItem(item);
        URI uri = URI.create(url + createdItem.getId());

        return ResponseEntity
                .created(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdItem);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteItem(@RequestBody @Valid DeleteItemRequestDTO dto){
        Item item = itemService.deleteItem(dto.getId());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Item: " + item + " deleted with reason: \"" + dto.getReason() +"\"");
    }
}
