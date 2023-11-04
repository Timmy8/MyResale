package com.example.myresale.controllers.advices;


import com.example.myresale.controllers.ItemsController;
import com.example.myresale.exceptions.ItemNotFoundException;
import com.example.myresale.services.InteractionWithItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.mockito.Mockito.*;

/*class GlobalControllerExceptionHandlerTest {

    @Mock
    ItemsController controller;

    @Test
    @DisplayName("GET /items{id} HTTP-request with Invalid id catched and return valid Response Entity")
    void getFindItemById_WithInvalidId_ReturnValidResponseEntity() throws Exception {
        //given
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalControllerExceptionHandler()).build();
        String id = "1";
        when(this.controller.findItemById("1")).thenThrow(new ItemNotFoundException(1));

        //when
        mockMvc.perform(get("/item/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Can't find item with id: " + id));





    }
}
*/