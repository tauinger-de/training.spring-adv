package com.example.pizza.product;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProductSetup {

    //
    // injected beans
    //

    private final ProductService productService;

    //
    // constructors and setup
    //

    public ProductSetup(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void createProducts() {
        createProduct("S-01", "Thunfisch Salat", 6.90);
        createProduct("S-02", "Salat Italiano", 7.90);
        createProduct("S-03", "Romana Salat", 8.90);
        createProduct("P-10", "Pizza Margarita", 5.50);
        createProduct("P-11", "Pizza Capricciosa", 7.50);
        createProduct("P-12", "Pizza Spinat und Feta", 7.00);
    }

    //
    // business logic
    //

    private void createProduct(String productId, String name, double price) {
        this.productService.createProduct(new Product(productId, name, price));
    }
}
