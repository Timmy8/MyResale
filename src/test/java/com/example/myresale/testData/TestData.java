package com.example.myresale.testData;

import com.example.myresale.entities.Item;

import java.math.BigDecimal;
import java.util.List;

public class TestData {
    private static List<Item> validItems = List.of(
            new Item(1L, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1"),
            new Item(2L, "Spring fast", "Good guide for Spring newbie", "Uolls", new BigDecimal(150), "google.com/photos/2"),
            new Item(3L, "TestBook", null, null, new BigDecimal(25), null )
    );

    private static List<Item> invalidItems = List.of(
            new Item(1L, null, null, null, null, null)
    );


    public static List<Item> getValidItemsList(){
        return validItems;
    }

    public static Item getValidItem(){
        return validItems.get(0);
    }

    public static Item getInvalidItem(){
        return invalidItems.get(0);
    }
}
