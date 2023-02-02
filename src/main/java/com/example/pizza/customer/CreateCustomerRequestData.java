package com.example.pizza.customer;

public class CreateCustomerRequestData {

    private final String fullName;

    private final Address address;

    private final String phoneNumber;

    public CreateCustomerRequestData(String fullName, Address address, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
