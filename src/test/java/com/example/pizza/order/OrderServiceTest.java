package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerRepository;
import com.example.pizza.product.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Map;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerRepository customerRepository;

    String customerPhoneNumber;

    @Test
    void placeOrder_customerOrderCountIncreasesDespiteTransactionFail() {
        // setup test
        customerPhoneNumber = "123456789";
        Customer customer = customerRepository.save(new Customer("Trans Action", null, customerPhoneNumber));

        // check count before test
        Assertions.assertEquals(0, customerRepository.findById(customer.getId()).get().getOrderCount());

        // place order for missing product (we don't have any product at this point)
        Map<String, Integer> itemQuantities = Collections.singletonMap("whatever", 1);
        Assertions.assertThrows(
                ProductNotFoundException.class,
                () -> orderService.placeOrder(customerPhoneNumber, itemQuantities));

        // check count after test
        Assertions.assertEquals(1, customerRepository.findById(customer.getId()).get().getOrderCount());
    }
}
