package com.example.pizza.order;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("default | order")
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
