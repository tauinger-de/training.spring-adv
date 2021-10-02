package com.example.pizza.product;

import com.example.pizza.error.ManagedException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ManagedException {

    public ProductNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
