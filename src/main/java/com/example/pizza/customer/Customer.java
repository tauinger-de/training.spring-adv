package com.example.pizza.customer;

import javax.persistence.*;

@Embeddable
@SuppressWarnings("unused")
public class Customer {

    //
    // --- fields ---
    //

    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_fullname")
    private String fullName;

    @Embedded
    private Address address;

    @Column(name = "customer_phone")
    private String phoneNumber;

    //
    // --- constructors ---
    //

    public Customer() {
    }

    public Customer(long id, String fullName, Address address, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //
    // --- get / set ---
    //

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
                '}';
    }
}
