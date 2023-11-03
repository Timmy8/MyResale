package com.example.myresale.services;

import com.example.myresale.entities.Item;
import com.example.myresale.exceptions.ItemNotFoundException;
import com.example.myresale.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionWithItemService {
    private final ItemRepository repository;

    public InteractionWithItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> findAllItems(){
        return repository.findAll();
    }

    public Item findItemById(long id){
        return repository.findById(id).orElseThrow(()-> new ItemNotFoundException(id));
    }

    public Item addItem(Item item){
        return repository.save(item);
    }
}
