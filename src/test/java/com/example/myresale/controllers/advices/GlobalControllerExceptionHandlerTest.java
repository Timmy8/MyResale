package com.example.myresale.controllers.advices;


/*class GlobalControllerExceptionHandlerTest {

    @Mock
    ItemsRestController controller;

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