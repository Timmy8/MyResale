package com.example.myresale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserCart {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    UserInfo user;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "ITEM_FROM_CART",
            joinColumns = {@JoinColumn(name = "user_cart_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")}
    )
    private List<Item> items = new ArrayList<>();

    public List<Item> getAllItemsFromCart(){return items;}
    public void addItemToCart(Item item){
        items.add(item);
    }
    public void deleteItemFromCart(int index){
        items.remove(index);
    }
    public void clearUserCart(){items.clear();}
}
