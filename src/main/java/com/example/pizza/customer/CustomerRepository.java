package com.example.pizza.customer;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Profile("default | customer | order")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByPhoneNumber(String phoneNumber);

}
