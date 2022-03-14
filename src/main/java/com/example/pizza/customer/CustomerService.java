package com.example.pizza.customer;

import com.example.pizza.aop.LogExecutionTime;
import com.example.pizza.product.ProductNotFoundException;
import feign.FeignException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("default | customer | order")
public class CustomerService {

    //
    // injected beans
    //

    private final CustomerApiClient customerApiClient;

    //
    // constructors and setup
    //

    public CustomerService(CustomerApiClient customerApiClient) {
        this.customerApiClient = customerApiClient;
    }

    //
    // business logic
    //

    @NonNull
    @LogExecutionTime
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        var customers = customerApiClient.findMany(Map.of("phoneNumber", phoneNumber));
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`");
        }
        else {
            // we don't deal with the issue of receiving more than 1 customer yet...
            return customers.iterator().next();
        }
    }

}
