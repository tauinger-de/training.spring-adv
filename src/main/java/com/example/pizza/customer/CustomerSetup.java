package com.example.pizza.customer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@ConditionalOnProperty(prefix = "app.setup", name = "customers", havingValue = "true")
public class CustomerSetup {

    private CustomerService customerService;
    private Map<String, Address> addresses;

    public CustomerSetup(CustomerService customerService, Map<String, Address> addresses) {
        this.customerService = customerService;
        this.addresses = addresses;
    }

    @PostConstruct
    public void createCustomers() {
        createCustomer(
                "Enrico Palazzo",
                addresses.get("münchen"),
                "123-4567");
        createCustomer(
                "Frank Drebbin",
                addresses.get("bonn"),
                "123-667788");
    }

    private Customer createCustomer(String fullName, Address address, String phoneNumber) {
        return this.customerService.createCustomer(new Customer(fullName, address, phoneNumber));
    }
}
