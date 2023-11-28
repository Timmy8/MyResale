package com.example.myresale.controllers;

import com.example.myresale.configuration.TelegramBotConfiguration;
import com.example.myresale.services.ItemService;
import com.example.myresale.telegramBot.MyResaleNotificationBot;
import com.example.myresale.util.ItemsRepo;
import com.example.myresale.util.UsersAndDTOs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    All tests started from Authenticated user with ROLE_USER,
    Anonymous user should be tested in ProjectConfiguration tests
*/
@WebMvcTest(controllers = ItemCreateController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
public class ItemCreateControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    ItemService itemService;
    @MockBean
    MyResaleNotificationBot bot;

    @Test
    @WithMockUser(roles = "USER")
    public void itemCreateForm_ReturnStatusOkAndValidView() throws Exception{
        // when
        var result = mvc.perform(get("/create"));

        // then
        result.andExpect(status().isOk())
                .andExpect(view().name("form_item_creation.html"));
    }

    @Test
    public void createItem_WithValidCreateItemRequestDto_ReturnStatusOkAndValidRedirect() throws Exception{
        // given
        var item = ItemsRepo.item();
        var user = UsersAndDTOs.validUserInfo();
        var createItemDto = UsersAndDTOs.validCreateRequestDto();

        // when
        when(itemService.addItem(createItemDto)).thenReturn(item);
        var result = mvc.perform(post("/create")
                .with(user(user))
                .flashAttr("createItemDTO", createItemDto));

        // then
        result.andExpect(status().isFound())
                .andExpect(redirectedUrlTemplate("/items/{id}", item.getId()));
    }

    @Test
    public void createItem_WithInvalidCreateItemRequestDto_ReturnStatusOkAndValidView() throws Exception{
        // given
        var user = UsersAndDTOs.validUserInfo();
        var createItemDto = UsersAndDTOs.invalidCreateRequestDto();

        // when
        var result = mvc.perform(post("/create")
                .with(user(user))
                .flashAttr("createItemDTO", createItemDto));

        // then
        result.andExpect(status().isOk())
                .andExpect(view().name("form_item_creation.html"))
                .andExpect(model().attributeExists("errors"));
    }

}
