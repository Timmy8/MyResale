package com.example.myresale.controllers;

import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.configuration.TelegramBotConfiguration;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.exceptions.UserExistsException;
import com.example.myresale.services.UserInfoDetailsService;
import com.example.myresale.util.UsersAndDTOs;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    All tests started from Anonymous user,
    User with some ROLE should be tested in ProjectConfiguration tests
*/
@WebMvcTest(controllers = UserRegistrationController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
public class UserRegistrationControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    UserInfoDetailsService userInfoService;

    @Test
    void registerForm_ReturnStatusOkAndValidView() throws Exception{
        // when
        var result = mvc.perform(get("/registration"));

        //then
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("page_registration.html"));
    }

    @Test
    void processRegistration_WithValidDataAndAnonymUser_ReturnStatusFoundAndRedirectToLoginPageWithOkMessage() throws Exception{
        // given
        UserInfoCreateDTO validDto = UsersAndDTOs.validUserDto();
        UserInfo validUser = UsersAndDTOs.validUserInfo();
        Mockito.when(userInfoService.saveUserInfo(validDto)).thenReturn(validUser);

        // when
        var result = mvc.perform(post("/registration").flashAttr("UserInfoDTO", validDto));

        // then
        result.andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("/login?registered=ok"));
    }

    @Test
    void processRegistration_WithInvalidDataAndAnonymUser_ReturnStatusOkAndErrorsAttribute() throws Exception{
        // given
        UserInfoCreateDTO invalidDto = UsersAndDTOs.invalidUserDto();

        // when
        var result = mvc.perform(post("/registration").flashAttr("UserInfoDTO", invalidDto));

        // then
        result.andExpect(status().isOk())
                .andExpect(model().attributeExists("errors"));
    }

    @Test
    void processRegistration_WithValidDataAndAnonymUser_ReturnStatusOkAndErrorsAttribute() throws Exception{
        // given
        UserInfoCreateDTO validDto = UsersAndDTOs.validUserDto();
        UserInfo validUser = UsersAndDTOs.validUserInfo();
        Mockito.when(userInfoService.saveUserInfo(validDto)).thenThrow(new UserExistsException());

        // when
        var result = mvc.perform(post("/registration").flashAttr("UserInfoDTO", validDto));

        // then
        result.andExpect(status().isOk())
                .andExpect(model().attribute("errors", "User already exists!"));
    }

}
