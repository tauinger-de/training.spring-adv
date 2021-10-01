package com.example.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class PizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

    @Bean("greeting")
    @ConditionalOnResource(resources = {"classpath:/greeting.txt"})
    public String resourceString() {
        try {
            return StreamUtils.copyToString(
                    getClass().getResourceAsStream("/greeting.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}
