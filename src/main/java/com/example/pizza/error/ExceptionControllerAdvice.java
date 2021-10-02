package com.example.pizza.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ManagedException.class)
    public ResponseEntity<ExceptionDto> handleManagedException(ManagedException ex) {
        return new ResponseEntity<>(
                ExceptionDto.of(ex),
                ex.getStatus());
    }
}
