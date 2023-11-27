package com.example.myresale.controllers;

import com.example.myresale.configuration.TelegramBotConfiguration;
import com.example.myresale.entities.Item;
import com.example.myresale.services.ItemService;
import com.example.myresale.util.ItemsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    All tests started from Anonymous user,
    User with some ROLE should be tested in ProjectConfiguration tests
*/
@WebMvcTest(controllers = ItemsMainController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
public class ItemsMainControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ItemService itemService;

    @Test
    public void findAllItems_WithoutParam_ReturnStatusOkAndValidViewAndItemList() throws Exception{
        // given
        var items = ItemsRepo.items();
        when(itemService.findAllItems()).thenReturn(items);

        // when
        var result = mvc.perform(get("/items"));

        //then
        result.andExpect(status().isOk())
                .andExpect(view().name("page_items_main.html"))
                .andExpect(model().attribute("items", items));
    }

    @Test
    public void findAllItems_WithParam_ReturnStatusOkAndValidViewAndValidItemList() throws Exception{
        // given
        var item = ItemsRepo.item();
        when(itemService.findAllItemsByKeyword("Item1")).thenReturn( List.of(item));

        // when
        var result = mvc.perform(get("/items").param("keyword", item.getName()));

        //then
        result.andExpect(status().isOk())
                .andExpect(view().name("page_items_main.html"))
                .andExpect(model().attribute("items", List.of(item)));
    }

    @Test
    public void findItemById_WithValidPathVariable_ReturnValidViewAndValidItem() throws Exception{
        // given
        Item item = ItemsRepo.item();
        when(itemService.findItemById(item.getId())).thenReturn(item);

        // when
        var result = mvc.perform(get("/items/{id}", item.getId()));

        //then
        result.andExpect(status().isOk())
                .andExpect(view().name("page_item_info.html"))
                .andExpect(model().attribute("item", item));

    }
}
