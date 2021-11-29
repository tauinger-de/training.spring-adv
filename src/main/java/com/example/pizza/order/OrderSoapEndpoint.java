package com.example.pizza.order;

import com.example.pizza.order.soap.PlaceOrderRequest;
import com.example.pizza.order.soap.PlaceOrderResponse;
import com.example.pizza.order.soap.QuantityMap;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
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
    public PlaceOrderResponse placeOrder(@RequestPayload PlaceOrderRequest placeOrderRequest) {
        Order order = orderService.placeOrder(
                placeOrderRequest.getPhoneNumber(),
                toHashMap(placeOrderRequest.getProductQuantities())
        );
        return toPlaceOrderResponse(order);
    }


    private PlaceOrderResponse toPlaceOrderResponse(Order order) {
        try {
            PlaceOrderResponse response = new PlaceOrderResponse();
            response.setTotalPrice(order.getTotalPrice());
            response.setEstimatedTimeOfDelivery(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(
                            order.getEstimatedTimeOfDelivery().toString()));
            return response;
        } catch (DatatypeConfigurationException e) {
            throw new TypeNotPresentException("XMLGregorianCalendar", e);
        }
    }


    private Map<String, Integer> toHashMap(QuantityMap productQuantities) {
        Map<String, Integer> result = new HashMap<>();
        productQuantities.getMapEntry().forEach(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }
}
