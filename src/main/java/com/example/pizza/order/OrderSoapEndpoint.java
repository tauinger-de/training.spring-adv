package com.example.pizza.order;

import com.example.pizza.order.soap.PlaceOrderRequest;
import com.example.pizza.order.soap.QuantityMap;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.HashMap;
import java.util.Map;

@Endpoint
public class OrderSoapEndpoint {

    private static final String NAMESPACE = "http://soap.order.pizza.example.com";

    private final OrderService orderService;

    public OrderSoapEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "placeOrderRequest")
    @ResponsePayload
    public void placeOrder(@RequestPayload PlaceOrderRequest placeOrderRequest) {
        System.out.println(placeOrderRequest);
        orderService.placeOrder(
                placeOrderRequest.getPhoneNumber(),
                toHashMap(placeOrderRequest.getProductQuantities())
        );
    }

    private Map<String, Integer> toHashMap(QuantityMap productQuantities) {
        Map<String, Integer> result = new HashMap<>();
        productQuantities.getMapEntry().forEach(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }
}
