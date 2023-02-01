package com.example.pizza.product;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.List;

public class ProductCsvConverter extends AbstractHttpMessageConverter<List<Product>> {

    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected List<Product> readInternal(Class<? extends List<Product>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(List<Product> products, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
