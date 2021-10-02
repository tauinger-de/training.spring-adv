package com.example.pizza.error;

import org.springframework.http.HttpStatus;

public class ExceptionDto {

    HttpStatus status;

    String type;

    String message;

    public ExceptionDto(HttpStatus status, String type, String message) {
        this.status = status;
        this.type = type;
        this.message = message;
    }

    public static ExceptionDto of(ManagedException ex) {
        return new ExceptionDto(ex.getStatus(), ex.getClass().getSimpleName(), ex.getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
