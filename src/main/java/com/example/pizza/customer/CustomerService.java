package com.example.pizza.customer;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    //
    // constructors and setup
    //

    public CustomerService() {
    }

    //
    // business logic
    //

    @NonNull
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        // TODO
        throw new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`");
    }

    @NonNull
    public Iterable<Customer> getAllCustomers() {
        // TODO
        return null;
    }

    @NonNull
    public Customer createCustomer(Customer customer) {
        // TODO
        return null;
    }

}
