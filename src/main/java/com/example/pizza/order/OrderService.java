package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerService;
import com.example.pizza.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Profile("default | order")
public class OrderService {

    //
    // constants
    //

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    //
    // fields
    //

    // use kebab-case!
    @Value("${app.order.delivery-time-in-minutes}")
    Integer deliveryTimeInMinutes;

    @Value("#{${app.order.daily-discounts}}")
    Map<String, Double> dailyDiscounts;

    //
    // injected beans
    //

    private final CustomerService customerService;

    private final ProductService productService;

    private final OrderRepository orderRepository;

    private final String greeting;

    //
    // constructors and setup
    //

    public OrderService(
            CustomerService customerService,
            ProductService productService,
            OrderRepository orderRepository,
            @Qualifier("greeting") String greeting
    ) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.greeting = greeting;
    }

    @PostConstruct
    public void dumpConfig() {
        System.out.println("Configured delivery time in minutes: " + this.deliveryTimeInMinutes);
    }

    //
    // business logic
    //

    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeOrder(String phoneNumber, Map<String, Integer> productQuantities) {
        // greet
        if (StringUtils.hasText(this.greeting)) System.out.println(this.greeting);

        // make sure customer exists -- throws exception if it doesn't
        Customer customer = this.customerService.getCustomerByPhoneNumber(phoneNumber);

        // calculate total price
        Double totalPrice = this.productService.getTotalPrice(productQuantities);

        // discounts
        String nameOfDayOfWeek = LocalDate.now().getDayOfWeek().name();
        Double discountRate = this.dailyDiscounts.getOrDefault(nameOfDayOfWeek, 0.0);
        Double discountedTotalPrice = totalPrice * (1.0 - discountRate / 100.0);
        LOG.debug("Reducing price of order from {} to {} due to today's discount of {}%", totalPrice, discountedTotalPrice, discountRate);

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
