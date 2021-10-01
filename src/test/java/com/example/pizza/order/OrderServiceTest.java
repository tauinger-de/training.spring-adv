package com.example.pizza.order;

import com.example.pizza.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerRepository customerRepository;

    String customerPhoneNumber = "123456789";

}
