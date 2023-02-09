package com.example.pizza;

import com.example.pizza.order.OrderProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableConfigurationProperties(OrderProperties.class)
public class PizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

    @Bean("greeting")
    @ConditionalOnResource(resources = "classpath:/greeting.txt")
    public String resourceString() {
        try {
            return StreamUtils.copyToString(
                    getClass().getResourceAsStream("/greeting.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }

    // this fixes the problem that the application won't start anymore after adding spring-integration libraries since
    // a second ConversionService instance is added by those. the solution is to make the MVC one a 'primary' bean, which
    // is used in case of ambiguity
    @Bean
    @Primary
    public ConversionService primaryConversionService(@Qualifier("mvcConversionService") ConversionService conversionService) {
        return conversionService;
    }
}
