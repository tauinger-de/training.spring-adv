package com.example.pizza.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductRestController.class)
@ContextConfiguration(classes = {ProductService.class})
public class ProductRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepository productRepository;

    /**
     * Uploads products via CSV
     */
    @Test
    void uploadProductsCsv() throws Exception {
        // given
        String csvData = "ABC-123,Coca Cola, 1.99";

        // when
        var resultActions = this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(ProductRestController.UPLOAD_CSV_ENDPOINT)
                        .contentType("text/csv")
                        .content(csvData))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
