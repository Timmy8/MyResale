package com.example.myresale.controllers;

import com.example.myresale.configuration.TelegramBotConfiguration;
import com.example.myresale.services.PurchaseOrderService;
import com.example.myresale.services.UserCartService;
import com.example.myresale.util.ItemsRepo;
import com.example.myresale.util.UsersAndDTOs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    All tests started from Authenticated user with ROLE_USER,
    Anonymous user should be tested in ProjectConfiguration tests
*/
@WebMvcTest(controllers = UserCartController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
public class UserCartControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    UserCartService cartService;
    @MockBean
    PurchaseOrderService orderService;

    @Test
    public void cartView_ReturnStatusOkValidViewAndValidItems() throws Exception{
        // given
        var items = ItemsRepo.items();
        var user = UsersAndDTOs.validUserInfo();
        when(cartService.getAllItemsFromUserCart(user.getId())).thenReturn(Set.copyOf(items));

        // when
        var result = mvc.perform(get("/cart").with(user(user)));

        // then

        result.andExpect(status().isOk())
                .andExpect(view().name("page_user_cart.html"))
                .andExpect(model().attribute("items",Set.copyOf(items)))
                .andExpect(model().attributeExists("fullPrice"));
    }

    @Test
    public void addItem_ReturnStatusFoundAndValidRedirect () throws Exception{
        // given
        var item = ItemsRepo.item();
        var user = UsersAndDTOs.validUserInfo();

        // when
        var result = mvc.perform(post("/cart").param("id",item.getId().toString()).with(user(user)));

        // then
        result.andExpect(status().isFound())
                .andExpect(redirectedUrl("/items"));
    }

    @Test
    public void clearCart_ReturnStatusFoundAndValidRedirect () throws Exception{
        // given
        var user = UsersAndDTOs.validUserInfo();

        // when
        var result = mvc.perform(get("/cart/clear").with(user(user)));

        // then
        result.andExpect(status().isFound())
                .andExpect(redirectedUrl("/cart"));
    }

    @Test
    public void deleteItem_ReturnStatusFoundAndValidRedirect () throws Exception{
        // given
        var item = ItemsRepo.item();
        var user = UsersAndDTOs.validUserInfo();

        // when
        var result = mvc.perform(get("/cart/delete").param("id",item.getId().toString()).with(user(user)));

        // then
        result.andExpect(status().isFound())
                .andExpect(redirectedUrl("/cart"));
    }

    @Test
    public void userPurchases_ReturnStatusOkAndValidView () throws Exception{
        // given
        var user = UsersAndDTOs.validUserInfo();

        // when
        var result = mvc.perform(get("/cart/purchases").with(user(user)));

        // then
        result.andExpect(status().isOk())
                .andExpect(view().name("page_user_purchases.html"))
                .andExpect(model().attributeExists("purchases"));
    }

}
