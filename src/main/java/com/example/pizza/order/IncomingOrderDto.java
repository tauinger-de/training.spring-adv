package com.example.pizza.order;

import java.util.Map;

public class IncomingOrderDto {

    public String phoneNumber;

    public Map<String, Integer> itemQuantities;

    @Override
    public String toString() {
        return "IncomingOrderDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", itemQuantities=" + itemQuantities +
                '}';
    }
}
