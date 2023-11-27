package com.example.myresale.controllers;

import com.example.myresale.DTOs.ItemDeleteRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = {"/delete", "/delete/{id}"})
public class ItemDeleteController {
    private ItemService itemService;
    Logger logger = Logger.getLogger(ItemDeleteController.class.getName());

    public ItemDeleteController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String deleteItem(@PathVariable(value = "id", required = false) Long id, Model model){
        model.addAttribute("id", id);

        return "form_item_deletion.html";
    }

    @PostMapping
    public String deleteItem(@ModelAttribute("deleteItemDTO") @Valid ItemDeleteRequestDTO dto,
                                             Errors errors, Model model){

        if (errors.hasErrors()) {
            List<String> errorsList = new ArrayList<>();

            errors.getAllErrors().forEach(error -> errorsList.add(error.getDefaultMessage()));
            model.addAttribute("errors", errorsList);
        } else {
            Item item = itemService.deleteItem(dto.getId());
            String message = "successfully deleted item #" + item.getId();
            logger.info(message);
            model.addAttribute("message", message);
        }

        return "form_item_deletion.html";
    }
}
