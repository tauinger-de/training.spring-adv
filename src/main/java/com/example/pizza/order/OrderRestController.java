package com.example.pizza.order;

import com.example.pizza.error.ExceptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("default | order")
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
        return StringUtils.hasText(this.greeting) ? this.greeting : "Hello!";
    }

    @Operation(summary = "Place an order", description = "Places an order by providing your phone number and a set of item quantities. " +
            "This is a map of product-ids to the number of products you want.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "The order has been placed successfully"),
            @ApiResponse(responseCode = "404", description = "Either a required Customer or Product entity could not be found - check details",
                    content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
    })
    @PostMapping(PLACE_ORDER_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody OrderRequestData orderRequestData) {
        return this.orderService.placeOrder(
                orderRequestData.phoneNumber,
                orderRequestData.itemQuantities);
    }

    @GetMapping(GET_MANY_ENDPOINT)
    public Iterable<Order> getOrders() {
        return this.orderService.getOrders();
    }
}
