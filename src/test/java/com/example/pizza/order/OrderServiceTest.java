package com.example.pizza.order;

import com.example.pizza.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

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
