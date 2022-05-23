package com.example.pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    final String productId = "the-product-id";
    final String productName = "blah";
    final Double productPrice = 1.23;

    /**
     * Tests that an exception is thrown if we try to create multiple products having the same id
     */
    @Test
    @Disabled
    void createProduct_failsForDuplicateProductId() {
        // given - create product
        productService.createProduct(new Product(productId, productName, productPrice));

        // when / then - call service as lambda and check instance of expected exception
        Assertions.assertThatThrownBy(
                () -> productService.createProduct(new Product(productId, productName, productPrice))
        ).isInstanceOf(IllegalStateException.class);
    }
}