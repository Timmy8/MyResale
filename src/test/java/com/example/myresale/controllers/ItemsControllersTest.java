package com.example.myresale.controllers;

import com.example.myresale.entities.DeleteItemRequestDTO;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.CreateItemRequestDTO;
import com.example.myresale.services.InteractionWithItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemsControllersTest {

    @Mock
    InteractionWithItemService service;

    @InjectMocks
    ItemsController controller;

    @Test
    @DisplayName("GET /items return HTTP-response with status OK(200) and All Founded Entities")
    void handleFindAllItems_ReturnValidResponseEntity(){
        //  given
        var items = List.of(
                new Item(1, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1"),
                new Item(2, "Spring fast", "Good guide for Spring newbie", "Uolls", new BigDecimal(150), "google.com/photos/2"),
                new Item(3, "TestBook", null, null, new BigDecimal(25), null )
        );

        doReturn(items).when(this.service).findAllItems();

        //when
        var responseEntity = controller.findAllItems();

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(items, responseEntity.getBody());
    }

    @Test
    @DisplayName("GET items/{id} return HTTP-response with status OK(200) and Valid Item")
    void findItemById_WithValidPathVar_ReturnValidResponseEntity(){
        //given
        var item = new Item(1, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");
        String id = "1";

        doReturn(item).when(this.service).findItemById(item.getId());
        //when
        var responseEntity = this.controller.findItemById(id);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(item, responseEntity.getBody());
    }

    @Test
    @DisplayName("POST /items with valid CreateItemRequestDTO return HTTP-response with status CREATED(201), Valid URI and Valid Item")
    void addItem_WithValidaRequestDTO_ReturnValidUriAndResponseEntity() {
        //given
        var item = new Item(1, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");
        URI uri = URI.create("http://localhost:8080/1");
        CreateItemRequestDTO itemDTO = new CreateItemRequestDTO("Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");

        doReturn(item).when(this.service).addItem(itemDTO);
        //when
        var responseEntity = controller.addItem(itemDTO);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(uri, responseEntity.getHeaders().getLocation());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(item, responseEntity.getBody());
    }

    @Test
    @DisplayName("DELETE /items with valid deleteItemRequestDTO return HTTP-response with status OK(200) and Valid Response Entity")
    void deleteItem_WithValidDeleteRequestDTO_ReturnValidResponseEntity(){
        //given
        var item = new Item(1, "Spring in Action", "Full guide for Spring newbie", "Uolls", new BigDecimal(15), "google.com/photos/1");
        DeleteItemRequestDTO itemDTO = new DeleteItemRequestDTO(1, "purchased");

        doReturn(item).when(this.service).deleteItem(item.getId());
        //when
        var responseEntity = this.controller.deleteItem(itemDTO);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals("Item: " + item + " deleted with reason\"" + itemDTO.getReason() +"\"", responseEntity.getBody());
    }
}