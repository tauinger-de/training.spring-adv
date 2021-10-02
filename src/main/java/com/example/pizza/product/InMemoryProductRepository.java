package com.example.pizza.product;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryProductRepository implements ProductRepository {

    //
    // fields
    //

    final Map<String, Product> productsById = new HashMap<>();

    //
    // ProductRepository implementation
    //

    @Override
    public Iterable<Product> findAll() {
        return this.productsById.values();
    }

    @Override
    public Optional<Product> findById(String productId) {
        return Optional.of(this.productsById.get(productId));
    }

    @Override
    public boolean existsById(String productId) {
        return this.productsById.containsKey(productId);
    }

    @Override
    public Product save(Product product) {
        return this.productsById.put(product.getProductId(), product);
    }

}
