package com.example.myresale.util;

import com.example.myresale.entities.Item;

import java.math.BigDecimal;
import java.util.List;

import static com.example.myresale.util.UsersAndDTOs.validUserInfo;

public class ItemsRepo {
        private static List<Item> items = List.of(
                new Item(1L, "Item1", "Description1", "Author1", new BigDecimal(1), "url1", false, validUserInfo()),
                new Item(2L, "Item2", "Description2", "Author1", new BigDecimal(2), "url2", true, validUserInfo()),
                new Item(3L, "Item3", "Description3", "Author1", new BigDecimal(3), "url3", false, validUserInfo())
        );
        public static List<Item> items(){
            return items;
        }
        public static Item item(){
            return items.get(0);
        }
}
