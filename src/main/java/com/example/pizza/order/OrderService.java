package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerService;
import com.example.pizza.product.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class OrderService {

    //
    // fields
    //

    @Value("${app.deliveryTimeInMinutes}")
    Integer deliveryTimeInMinutes;

    @Value("#{${app.dailyDiscounts}}")
    Map<String, Double> dailyDiscounts;

    //
    // injected beans
    //

    private CustomerService customerService;
    private ProductService productService;
    private OrderRepository orderRepository;
    private String greeting;

    //
    // constructors and setup
    //

    public OrderService(CustomerService customerService, ProductService productService, OrderRepository orderRepository,
                        @Qualifier("greeting") String greeting) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.greeting = greeting;
    }

    //
    // business logic
    //

    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeOrder(String phoneNumber, Map<String, Integer> productQuantities) {
        // greet
        System.out.println(this.greeting);

        // make sure customer exists -- throws exception if doesn't
        Customer customer = this.customerService.getCustomerByPhoneNumber(phoneNumber);

        // increase
        this.customerService.increaseOrderCount(customer.getId());

        // calculate total price
        Double totalPrice = this.productService.getTotalPrice(productQuantities);

        // discounts
        String nameOfDayOfWeek = LocalDate.now().getDayOfWeek().name();
        Double discountRate = this.dailyDiscounts.getOrDefault(nameOfDayOfWeek, 0.0);
        Double discountedTotalPrice = totalPrice * (1.0 - discountRate / 100.0);
        System.out.println("Reducing price of order from " + totalPrice + " to " + discountedTotalPrice
                + " due to today's discount of " + discountRate + "%");

        // create order
        Order order = new Order(
                customer,
                discountedTotalPrice,
                LocalDateTime.now().plusMinutes(this.deliveryTimeInMinutes));

        // persist and return it
        return this.orderRepository.save(order);
    }

    public Iterable<Order> getOrders() {
        return this.orderRepository.findAll();
    }
}
