package com.example.pizza.customer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerRequestDataConverter implements Converter<CreateCustomerRequestData, Customer> {

    @Override
    public Customer convert(CreateCustomerRequestData source) {
        return new Customer(
                source.getFullName(),
                source.getAddress(),
                source.getPhoneNumber()
        );
    }
}
