package com.example.pizza.product;

import java.util.Optional;

public interface ProductRepository {

    Iterable<Product> findAll();

    Optional<Product> findById(String productId);

    boolean existsById(String productId);

    Product save(Product product);

}
