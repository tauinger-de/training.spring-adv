package com.example.pizza.customer;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    //
    // injected beans
    //

    private final CustomerRepository repository;

    //
    // constructors and setup
    //

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    //
    // business logic
    //

    @NonNull
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return this.repository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`"));
    }

    @NonNull
    public Iterable<Customer> getAllCustomers() {
        return this.repository.findAll();
    }

    @NonNull
    public Customer createCustomer(Customer customer) {
        return this.repository.save(customer);
    }

}
