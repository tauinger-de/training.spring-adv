package com.example.pizza.product;

@SuppressWarnings("unused")
public class Product {

    //
    // --- fields ---
    //

    String productId;

    String name;

    Double price;

    //
    // --- constructors ---
    //

    public Product() {
    }

    public Product(String productId, String name, Double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    //
    // --- get / set ---
    //

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    //
    // --- misc ---
    //

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
