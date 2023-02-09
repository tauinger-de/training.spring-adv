package com.example.pizza.product;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// todo reactive webmvctest
//@WebMvcTest(ProductRestController.class)
//@ContextConfiguration(classes = {ProductService.class})
//@TestPropertySource(properties = {"logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG"})
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepository productRepository;

    // required for verifying call on mock since we don't know the exact List type
    @Captor
    ArgumentCaptor<List<Product>> productListCaptor;

    /**
     * Uploads products via CSV using a mocked ProductRepositoy
     */
    @Test
    void uploadProductsCsv() throws Exception {
        // given
        String csvData = "productId,name,price\nABC-123,Coca Cola, 1.99";
        Mockito.clearInvocations(productRepository);

        // when
        var resultActions = this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put(ProductRestController.UPLOAD_CSV_ENDPOINT)
                        .contentType("text/csv")
                        .content(csvData))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(productRepository).saveAll(productListCaptor.capture());
        var dataList = productListCaptor.getValue();
        assertThat(dataList).hasSize(1);

        ProductAssertions.assertProduct(dataList.get(0)).hasProductId("ABC-123");
        assertThat(dataList.get(0).getName()).isEqualTo("Coca Cola");
        assertThat(dataList.get(0).getPrice()).isEqualTo(1.99);
        assertThat(dataList.get(0)).hasFieldOrPropertyWithValue("productId", "ABC-123");
    }

}
