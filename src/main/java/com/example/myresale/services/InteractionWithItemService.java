package com.example.myresale.services;

import com.example.myresale.entities.Item;
import com.example.myresale.entities.CreateItemRequestDTO;
import com.example.myresale.exceptions.ItemNotFoundException;
import com.example.myresale.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Item addItem(CreateItemRequestDTO itemDTO){
        Item item = Item.builder()
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .author(itemDTO.getAuthor())
                .price(itemDTO.getPrice())
                .imageURL(itemDTO.getImageURL())
                .build();

        return repository.save(item);
    }

    @Transactional
    public Item deleteItem(long id){
        Item item = findItemById(id);
        repository.deleteItemById(id);

        return item;
    }
}
