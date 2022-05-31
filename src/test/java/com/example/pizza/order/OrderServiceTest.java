package com.example.pizza.order;

import com.example.pizza.customer.CustomerRepository;
import com.example.pizza.customer.CustomerSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerSetup customerSetup;

    /**
     * Make sure we have exactly the list of customers as provided by the CustomerSetup.
     */
    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        customerSetup.createCustomers();
    }

    /**
     * This test executes a simple order placement without any checks/assertions
     */
    @Test
    void placeOrder() {
        // given -- this data should exist due to CustomSetup and ProductSetup beans
        var existingCustomerPhoneNumber = "123-4567";
        var existingProductId = "S-01";

        // when
        orderService.placeOrder(
                existingCustomerPhoneNumber,
                Map.of(existingProductId, 1));
    }
}
