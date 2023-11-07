package com.example.myresale.services;

import com.example.myresale.entities.Item;
import com.example.myresale.entities.UserCart;
import com.example.myresale.repositories.UserCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCartService {
    private UserCartRepository repository;

    public UserCartService(UserCartRepository repository) {
        this.repository = repository;
    }

    private UserCart findUserCartById(long id){
        return repository.findById(id).orElseThrow();
    }

    public List<Item> getAllItemsFromUserCart(long userId){
        return findUserCartById(userId).getAllItemsFromCart();
    }

    @Transactional
    public void addItemToUserCart(long userId, Item item){
        findUserCartById(userId).addItemToCart(item);
    }

    @Transactional
    public void deleteItemFromUserCart(long userId, int itemIndex){
        findUserCartById(userId).deleteItemFromCart(itemIndex);
    }

    @Transactional
    public void clearUserCart(long userId){
        findUserCartById(userId).clearUserCart();
    }
}
