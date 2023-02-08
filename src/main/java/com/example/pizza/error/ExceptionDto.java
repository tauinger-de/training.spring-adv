package com.example.pizza.error;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

public class ExceptionDto {

    @Schema(example = "NOT_FOUND")
    final HttpStatus status;

    @Schema(example = "CustomerNotFoundException")
    final String type;

    @Schema(example = "For phoneNumber `123-456`")
    final String message;

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
