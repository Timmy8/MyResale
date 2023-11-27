package com.example.myresale.controllers;


import com.example.myresale.configuration.TelegramBotConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
    All tests started from Anonymous user,
    User with some ROLE should be tested in ProjectConfiguration tests
*/

@WebMvcTest(controllers = UserLoginController.class, excludeAutoConfiguration = {TelegramBotConfiguration.class})
public class UserLoginControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void loginPage_WithoutParam_ReturnStatusOkAndValidView() throws Exception{
        // when
        var result = mvc.perform(get("/login"));
        // then
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("page_login.html"));
    }

    @Test
    void loginPage_WithOkParam_ReturnStatusOkAndValidViewAndMessageAttribute() throws Exception{
        // when
        var result = mvc.perform(get("/login").param("registered", "ok"));

        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("page_login.html"))
                .andExpect(MockMvcResultMatchers.model().attribute("message", "Successfully registered!"));
    }
}
