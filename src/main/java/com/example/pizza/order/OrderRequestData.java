package com.example.pizza.order;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public class OrderRequestData {

    @Schema(description = "The customer's phone-number", example = "+49 5251 504322")
    public String phoneNumber;

    // unfortunately Swagger doesn't provide support for documenting a Map: https://github.com/swagger-api/swagger-core/issues/3080
    @Schema(description = "A mapping of product-ids to their ordered quantities")
    public Map<String, Integer> itemQuantities;

    @Override
    public String toString() {
        return "OrderRequestData{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", itemQuantities=" + itemQuantities +
                '}';
    }
}
