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
    ProductRepository productRepository;

    String productId = "the-product-id";
    String productName = "blah";
    Double productPrice = 1.23;

    @Test
    void getTotalPrice() {
        // mock test fixture
        Product someDish = new Product(productId, productName, productPrice);
        Mockito.when(this.productRepository.findById(productId)).thenReturn(Optional.of(someDish));

        // execute
        int quantity = 2;
        Double totalPrice = productService.getTotalPrice(Collections.singletonMap(productId, quantity));
        Assertions.assertEquals(productPrice * quantity, totalPrice);
    }
}