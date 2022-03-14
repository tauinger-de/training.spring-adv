package com.example.pizza.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
class ProductServiceTest_WithMocks {

    @Autowired
    ProductService productService;

    @MockBean
    ProductApiClient productApiClient;

    final String productId = "the-product-id";
    final String productName = "blah";
    final Double productPrice = 1.23;

    @Test
    void getTotalPrice() {
        // mock test fixture
        Product someDish = new Product(productId, productName, productPrice);
        Mockito.when(this.productApiClient.findById(productId)).thenReturn(someDish);

        // execute
        int quantity = 2;
        Double totalPrice = productService.getTotalPrice(Collections.singletonMap(productId, quantity));
        Assertions.assertEquals(productPrice * quantity, totalPrice);
    }
}