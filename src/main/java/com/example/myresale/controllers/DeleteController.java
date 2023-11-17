package com.example.myresale.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    @GetMapping("/{id}")
    public String deleteItem(@PathVariable(value = "id", required = false) Long id, Model model){
        model.addAttribute("id", id);

        return "form_item_deletion.html";
    }
}
