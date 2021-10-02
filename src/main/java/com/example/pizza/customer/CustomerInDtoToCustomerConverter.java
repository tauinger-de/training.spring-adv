package com.example.pizza.customer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerInDtoToCustomerConverter implements Converter<CustomerInDto, Customer> {

    @Override
    public Customer convert(CustomerInDto source) {
        return new Customer(
                source.getFullName(),
                source.getAddress(),
                source.getPhoneNumber()
        );
    }
}
