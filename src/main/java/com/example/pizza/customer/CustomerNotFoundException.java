package com.example.pizza.customer;

import com.example.pizza.error.ManagedException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ManagedException {

    public CustomerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
