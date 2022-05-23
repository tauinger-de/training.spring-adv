package com.example.pizza.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest_WithMocks {

    @Autowired
    ProductService productService;

    // @MockBean
    // ProductRepository productRepository;

    /**
     * Tests that the total price for a range of products and quantities is calculated correctly.
     */
    @Test
    void getTotalPrice() {
        // TODO
    }
}
