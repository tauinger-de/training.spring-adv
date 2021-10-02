package com.example.pizza.customer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConditionalOnProperty(prefix = "app.setup", name = "customers", havingValue = "true")
public class CustomerSetup {

    private CustomerService customerService;

    public CustomerSetup(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostConstruct
    public void createCustomers() {
        createCustomer(
                "Enrico Palazzo",
                new Address("Bornstr. 7", "44555", "Irgendwo"),
                "123-4567");
        createCustomer(
                "Frank Drebbin",
                new Address("Dorfweg 21", "99887", "Wildhausen"),
                "123-667788");
    }

    private Customer createCustomer(String fullName, Address address, String phoneNumber) {
        return this.customerService.createCustomer(new Customer(fullName, address, phoneNumber));
    }
}
