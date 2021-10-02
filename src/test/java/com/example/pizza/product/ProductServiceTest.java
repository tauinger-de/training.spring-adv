package com.example.pizza.product;

import org.junit.jupiter.api.Assertions;
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

    @Test
    void createProduct_failsForDuplicateProductId() {
        // first call creates product
        productService.createProduct(new Product(productId, productName, productPrice));

        // second call must throw exception
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> productService.createProduct(new Product(productId, productName, productPrice)));
    }
}