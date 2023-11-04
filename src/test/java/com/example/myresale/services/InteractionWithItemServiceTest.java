package com.example.myresale.services;

import com.example.myresale.entities.CreateItemRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.repositories.ItemRepository;
import org.hibernate.validator.internal.metadata.aggregated.FieldCascadable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InteractionWithItemServiceTest {
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    InteractionWithItemService service;

    @Test
    @DisplayName("findAllItems method call return valid List<Item>")
    void findAllItem_ReturnValidResponseEntity(){
        //given
        var items = List.of(
                new Item(1, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1"),
                new Item(2, "Spring fast", "Good guide for Spring newbie", "Uolls", new BigDecimal(150), "google.com/photos/2"),
                new Item(3, "TestBook", null, null, new BigDecimal(25), null )
        );

        doReturn(items).when(this.itemRepository).findAll();

        //when
        var responseEntity = this.service.findAllItems();

        //then
        assertNotNull(responseEntity);
        assertEquals(items, responseEntity);
    }

    @Test
    @DisplayName("findItemById method call with valid id return valid Item")
    void findItemById_WithValidId_ReturnValidItemEntity(){
        //given
        var item = new Item(1, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");
        Optional<Item> response = Optional.of(item);
        long id = 1;

        doReturn(response).when(this.itemRepository).findById(item.getId());
        //when
        var responseEntity = this.service.findItemById(id);

        //then
        assertNotNull(responseEntity);
        assertEquals(item, responseEntity);
    }

    /*
    @Test
    @DisplayName("AddItem method call with valid createItemRequestDTO return valid Item")
    void addItem_WithValidCreateItemRequestDTO_ReturnValidItem(){
        //given
        var returnedItem = new Item(0, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");
        var itemDTO = new CreateItemRequestDTO("Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");

        doReturn(returnedItem).when(this.itemRepository);
        //when
        var responseEntity = this.service.addItem(itemDTO);
        //then
        assertNotNull(responseEntity);
    }
    */

    @Test
    @DisplayName("deleteItem method call with valid id return valid deleted item")
    void deleteItem_WithValidId_ReturnValidDeletedItem(){
        //given
        var item = new Item(1, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");
        Optional<Item> itemOptional = Optional.of(item);
        long id = 1;

        doReturn(itemOptional).when(this.itemRepository).findById(item.getId());
        //when
        var responseEntity = this.service.deleteItem(id);

        //then
        assertNotNull(responseEntity);
        assertEquals(item,responseEntity);
    }
}