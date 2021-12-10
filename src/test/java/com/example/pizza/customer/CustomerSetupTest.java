package com.example.pizza.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"app.customer.perform-setup = false"})
public class CustomerSetupTest {

    final CustomerService customerService;

    @Autowired
    public CustomerSetupTest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void noCustomersExist() {
        Assertions.assertFalse(customerService.getAllCustomers().iterator().hasNext());
    }
}
