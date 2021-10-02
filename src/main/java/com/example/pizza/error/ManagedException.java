package com.example.pizza.error;

import org.springframework.http.HttpStatus;

public class ManagedException extends RuntimeException {

    private final HttpStatus status;

    public ManagedException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
