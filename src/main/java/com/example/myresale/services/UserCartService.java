package com.example.myresale.services;

import com.example.myresale.entities.Item;
import com.example.myresale.entities.UserCart;
import com.example.myresale.repositories.ItemRepository;
import com.example.myresale.repositories.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserCartService {
    private UserInfoRepository userInfoRepository;
    private ItemRepository itemRepository;

    public UserCartService(UserInfoRepository userInfoRepository, ItemRepository itemRepository) {
        this.userInfoRepository = userInfoRepository;
        this.itemRepository = itemRepository;
    }

    private UserCart findUserCartById(long id){
        return userInfoRepository.findById(id).orElseThrow().getUserCart();
    }

    public Set<Item> getAllItemsFromUserCart(long userId){
        return findUserCartById(userId).getAllItemsFromCart();
    }

    @Transactional
    public void addItemToUserCart(long userId, long itemID){
        findUserCartById(userId).addItemToCart(itemRepository.findById(itemID).orElseThrow());
    }

    @Transactional
    public void deleteItemFromUserCart(long userId, long itemId){
        findUserCartById(userId).deleteItemFromCart(itemRepository.findById(itemId).orElseThrow());
    }

    @Transactional
    public void clearUserCart(long userId){
        findUserCartById(userId).clearUserCart();
    }
}
