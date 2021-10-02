package com.example.pizza.address;

import com.example.pizza.customer.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {

    private final String street = "Test-Allee 1";
    private final String postalCode = "12345";
    private final String city = "Erbshausen";

    @Test
    public void instantiating() {
        Address address = new Address(street, postalCode, city);
        Assertions.assertEquals(street, address.getStreet());
        Assertions.assertEquals(postalCode, address.getPostalCode());
        Assertions.assertEquals(city, address.getCity());
    }
}
