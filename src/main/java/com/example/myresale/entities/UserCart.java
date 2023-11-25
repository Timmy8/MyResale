package com.example.myresale.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserCart {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "userCart")
    private UserInfo user;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name = "item_from_cart",
            joinColumns = @JoinColumn(name = "user_cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private final Set<Item> items = new HashSet<>();

    public Set<Item> getAllItemsFromCart(){return items;}
    public void addItemToCart(Item item){
        items.add(item);
    }
    public void deleteItemFromCart(Item item){
        items.remove(item);
    }
    public void clearUserCart(){items.clear();}
}
