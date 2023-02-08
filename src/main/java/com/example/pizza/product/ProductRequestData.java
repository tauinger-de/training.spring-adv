package com.example.pizza.product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// property order required for CsvMapper to be able to define order of values and header names
@JsonPropertyOrder(value = {"productId", "name", "price"})
public class ProductRequestData {

    private String productId;

    private String name;

    private Double price;

    public ProductRequestData() {
    }

    public ProductRequestData(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
