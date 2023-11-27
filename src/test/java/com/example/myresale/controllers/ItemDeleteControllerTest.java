package com.example.myresale.controllers;

import com.example.myresale.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class ItemDeleteControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ItemService itemService;


}
