package com.example.myresale.controllers.API;

import com.example.myresale.configuration.TelegramBotConfiguration;
import com.example.myresale.entities.Item;
import com.example.myresale.services.ItemService;
import com.example.myresale.util.ItemsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    All tests started from Anonymous user,
    User with some ROLE should be tested in ProjectConfiguration tests
*/
@WebMvcTest(controllers = GlobalRESTController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
public class GlobalRestControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ItemService itemService;

    @Test
    public void findAllItems_WithoutParam_ReturnStatusOkAndValidResponseEntity() throws Exception{
        // given
        var items = ItemsRepo.items();
        when(itemService.findAllItems()).thenReturn(items);

        // when
        var result = mvc.perform(get("/api/findAllItems"));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(items.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(items.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(items.get(2).getId()));
    }

    @Test
    public void findAllItems_WithParam_ReturnStatusOkAndValidResponseEntity() throws Exception{
        // given
        var item = ItemsRepo.item();
        when(itemService.findAllItemsByKeyword(item.getName())).thenReturn(List.of(item));

        // when
        var result = mvc.perform(get("/api/findAllItems").param("keyword", item.getName()));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(item.getId()));
    }

    @Test
    public void findItemById_WithValidPathVariable_ReturnValidViewAndValidItem() throws Exception{
        // given
        Item item = ItemsRepo.item();
        when(itemService.findItemById(item.getId())).thenReturn(item);

        // when
        var result = mvc.perform(get("/api/findItemById/{id}", item.getId()));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(item.getId()));

    }

    @Test
    public void itemAvailableById_WithValidPathVariable_ReturnStatusOkValidResponseEntity() throws Exception{
        // given
        var item = ItemsRepo.item();
        when(itemService.isAvailable(item.getId())).thenReturn(item.isAvailable());
        // when
        var result = mvc.perform(get("/api/isAvailable/{id}", item.getId()));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""+item.isAvailable()));

    }

}
