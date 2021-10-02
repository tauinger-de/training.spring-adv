package com.example.pizza.customer;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

    //
    // --- fields ---
    //

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;

    @Embedded
    private Address address;

    private String phoneNumber;

    private Integer orderCount = 0;

    //
    // --- constructors ---
    //

    public Customer() {
    }

    public Customer(String fullName, Address address, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //
    // --- get / set ---
    //

    public int increaseOrderCount() {
        return this.orderCount++;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    //
    // --- misc ---
    //

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderCount=" + orderCount +
                '}';
    }
}
