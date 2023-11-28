package com.example.myresale.controllers;

import com.example.myresale.configuration.TelegramBotConfiguration;
import com.example.myresale.services.ItemService;
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
@WebMvcTest(controllers = ItemDeleteController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
@WithMockUser(roles = "USER")
public class ItemDeleteControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ItemService itemService;

    @Test
    public void deleteItem_WithoutParam_ReturnStatusOkAndValidView() throws Exception{
        // when
        var result = mvc.perform(get("/delete"));

        // then
        result.andExpect(status().isOk())
                .andExpect(view().name("form_item_deletion.html"));
    }

    @Test
    public void deleteItem_WithValidParam_ReturnStatusOkAndValidViewAndValidAttribute() throws Exception{
        // when
        var result = mvc.perform(get("/delete/{id}", 1L));

        // then
        result.andExpect(status().isOk())
                .andExpect(view().name("form_item_deletion.html"))
                .andExpect(model().attribute("id", 1L));
    }


}
