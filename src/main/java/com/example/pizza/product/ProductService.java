package com.example.pizza.product;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    //
    // constructors and setup
    //

    public ProductService() {
    }

    //
    // business logic
    //

    public Iterable<Product> getAllProducts() {
        // TODO
        return null;
    }

    public Product getProduct(String productId) {
        // TODO
        throw new ProductNotFoundException("For productId `" + productId + "`");
    }

    public Double getTotalPrice(Map<String, Integer> productQuantities) {
        // TODO
        return null;
    }

    public Product createProduct(Product product) {
        // TODO
        return null;
    }
}
