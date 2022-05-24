package com.example.pizza;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WelcomingRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Welcome");
    }

}
