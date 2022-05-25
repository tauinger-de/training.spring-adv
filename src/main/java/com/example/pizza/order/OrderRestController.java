package com.example.pizza.order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    //
    // --- constants ---
    //

    static final String ROOT = "/orders";
    public static final String GREETING_ENDPOINT = ROOT + "/greeting";
    public static final String PLACE_ORDER_ENDPOINT = ROOT;
    public static final String GET_MANY_ENDPOINT = ROOT;

    //
    // --- injected beans ---
    //

    private final OrderService orderService;
    private final String greeting;

    //
    // --- constructors and setup ---
    //

    public OrderRestController(OrderService orderService, @Qualifier("greeting") String greeting) {
        this.orderService = orderService;
        this.greeting = greeting;
    }

    //
    // --- REST endpoints ---
    //

    @GetMapping(GREETING_ENDPOINT)
    public String sayHello() {
        return StringUtils.isEmpty(this.greeting) ? "Hello!" : this.greeting;
    }

    @PostMapping(PLACE_ORDER_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody IncomingOrderDto incomingOrderDto) {
        return this.orderService.placeOrder(
                incomingOrderDto.phoneNumber,
                incomingOrderDto.itemQuantities);
    }

    @GetMapping(GET_MANY_ENDPOINT)
    public Iterable<Order> getOrders() {
        return this.orderService.getOrders();
    }
}
