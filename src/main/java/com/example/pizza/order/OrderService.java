package com.example.pizza.order;

import com.example.pizza.customer.CustomerService;
import com.example.pizza.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    //
    // fields
    //

    Integer deliveryTimeInMinutes = 30;

    Map<String, Double> dailyDiscounts = new HashMap<>();

    //
    // injected beans
    //

    private CustomerService customerService;
    private ProductService productService;

    //
    // constructors and setup
    //

    public OrderService(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    //
    // business logic
    //

    public Order placeOrder(String phoneNumber, Map<String, Integer> productQuantities) {
        // TODO
        return null;
    }

    public Iterable<Order> getOrders() {
        // TODO
        return null;
    }
}
