package com.example.pizza.product;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("default | product | order")
public interface ProductRepository extends CrudRepository<Product, String> {
}
