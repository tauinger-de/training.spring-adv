package com.example.pizza.order;

import java.util.Map;

public class OrderRequestData {

    public String phoneNumber;

    public Map<String, Integer> itemQuantities;

    @Override
    public String toString() {
        return "OrderRequestData{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", itemQuantities=" + itemQuantities +
                '}';
    }
}
