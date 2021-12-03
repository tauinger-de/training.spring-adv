package com.example.pizza.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default | customer")
public class AddressSetup {

    @Bean("münchen")
    public Address createAddress1() {
        return new Address("Adenauerring 7", "81737", "München");
    }

    @Bean("bonn")
    public Address createAddress2() {
        return new Address("Graurheindorfer Str. 108", "53117", "Bonn");
    }

}
