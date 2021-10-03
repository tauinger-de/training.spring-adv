package com.example.pizza.order;

import com.example.pizza.customer.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "ORDERS") // just "order" is a reserved sql word!
public class Order {

    //
    // --- fields ---
    //

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Customer customer;

    Double totalPrice;

    LocalDateTime estimatedTimeOfDelivery;

    //
    // --- constructors ---
    //

    public Order() {
    }

    public Order(Customer customer, Double totalPrice, LocalDateTime estimatedTimeOfDelivery) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.estimatedTimeOfDelivery = estimatedTimeOfDelivery;
    }

    //
    // --- get / set ---
    //


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getEstimatedTimeOfDelivery() {
        return estimatedTimeOfDelivery;
    }

    public void setEstimatedTimeOfDelivery(LocalDateTime estimatedTimeOfDelivery) {
        this.estimatedTimeOfDelivery = estimatedTimeOfDelivery;
    }
}
