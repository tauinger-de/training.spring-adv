package com.example.pizza.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    //
    // --- constants ---
    //

    static final String ROOT = "/products";
    static final String GET_MANY_ENDPOINT = ROOT;

    //
    // --- injected beans ---
    //

    private final ProductService productService;

    //
    // --- constructors and setup ---
    //

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    //
    // --- REST endpoints ---
    //

    @GetMapping(GET_MANY_ENDPOINT)
    public Iterable<Product> getProducts() {
        return this.productService.getAllProducts();
    }
}
