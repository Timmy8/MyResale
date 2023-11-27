package com.example.myresale.controllers;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.configuration.TelegramBotConfiguration;
import com.example.myresale.services.ItemService;
import com.example.myresale.services.PurchaseOrderService;
import com.example.myresale.util.ItemsRepo;
import com.example.myresale.util.UsersAndDTOs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    All tests started from Authenticated user with ROLE_USER,
    Anonymous user should be tested in ProjectConfiguration tests
*/

@WebMvcTest(controllers = ItemPurchaseController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
@WithMockUser(roles = "USER")
public class ItemPurchaseControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ItemService itemService;
    @MockBean
    PurchaseOrderService purchaseOrderService;

    @Test
    public void itemPurchaseForm_WithValidPathVariable_ReturnStatusOkAndValidViewAndModelAttribute() throws Exception{
        // when
        var result = mvc.perform(get("/purchase/{id}", 1));

        // then
        result.andExpect(status().isOk())
                .andExpect(view().name("form_item_purchase.html"))
                .andExpect(model().attribute("itemId", 1));
    }

    @Test
    public void purchaseAllFromUserCart_ReturnStatusOkAndValidViewAndModelAttribute() throws Exception{
        // when
        var result = mvc.perform(get("/purchase/allCartPurchase"));

        // then
        result.andExpect(status().isOk())
                .andExpect(view().name("form_item_purchase.html"))
                .andExpect(model().attribute("itemId", "allCartPurchase"));
    }

    @Test
    public void purchaseItem_WithValidPathVariableAndValidDeliveryAddressCreateDtoAndAvailableItem_ReturnStatusOkAndValidResponseEntity() throws Exception{
        // given
        var item = ItemsRepo.item();
        var addressCreateDto = UsersAndDTOs.validDeliveryAddress();
        var user = UsersAndDTOs.validUserInfo();

        when(itemService.findItemById(item.getId())).thenReturn(item);
        when(purchaseOrderService.saveOrder(Set.of(item), addressCreateDto, user)).thenReturn(Optional.of(1L));

        // when

        var result = mvc.perform(post("/purchase/{id}", item.getId()).with(user(user)).flashAttr("deliveryAddress", addressCreateDto));

        // then
        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Thank you for your purchase\nPurchase number - " + 1L));
    }

    @Test
    public void purchaseItem_WithValidPathVariableAndInvalidDeliveryAddressCreateDtoAndAvailableItem_ReturnStatusBadRequestAndValidResponseEntity() throws Exception{
        // given
        var item = ItemsRepo.item();
        var validAddressCreateDto = UsersAndDTOs.validDeliveryAddress();
        var invalidAddressCreateDto = new DeliveryAddressCreateDTO();
        var user = UsersAndDTOs.validUserInfo();

        when(itemService.findItemById(item.getId())).thenReturn(item);
        when(purchaseOrderService.saveOrder(Set.of(item), validAddressCreateDto, user)).thenReturn(Optional.of(1L));

        // when

        var result = mvc.perform(post("/purchase/{id}", item.getId()).with(user(user)).flashAttr("deliveryAddress", invalidAddressCreateDto));

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Sorry, item is unavailable"));
    }

    @Test
    public void purchaseAllCart_WithValidDeliveryAddressCreateDtoAndAvailableItems_ReturnStatusOkAndValidResponseEntity() throws Exception{
        // given
        var item = ItemsRepo.item();
        var validAddressCreateDto = UsersAndDTOs.validDeliveryAddress();
        var user = UsersAndDTOs.validUserInfo();
        user.getUserCart().addItemToCart(item);

        when(purchaseOrderService.saveOrder(user.getUserCart().getAllItemsFromCart(), validAddressCreateDto, user)).thenReturn(Optional.of(1L));

        // when

        var result = mvc.perform(post("/purchase/allCartPurchase").with(user(user)).flashAttr("deliveryAddress", validAddressCreateDto));

        // then
        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Thank you for your purchase\nPurchase number - " + 1L));
    }

    @Test
    public void purchaseAllCart_WithInvalidDeliveryAddressCreateDtoAndAvailableItems_ReturnStatusOkAndValidResponseEntity() throws Exception{
        // given
        var item = ItemsRepo.item();
        var validAddressCreateDto = UsersAndDTOs.validDeliveryAddress();
        var user = UsersAndDTOs.validUserInfo();
        user.getUserCart().addItemToCart(item);

        when(purchaseOrderService.saveOrder(user.getUserCart().getAllItemsFromCart(), validAddressCreateDto, user)).thenReturn(Optional.of(1L));

        // when

        var result = mvc.perform(post("/purchase/allCartPurchase").with(user(user)).flashAttr("deliveryAddress", new DeliveryAddressCreateDTO()));

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Sorry, item is unavailable"));
    }
}
