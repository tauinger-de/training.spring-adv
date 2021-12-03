package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerRepository;
import com.example.pizza.product.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerRepository customerRepository;

    final String customerPhoneNumber = "123456789";

    @Test
    void placeOrder_customerOrderCountIncreasesDespiteTransactionFail() {
        // setup test
        Customer customer = customerRepository.save(new Customer("Trans Action", null, customerPhoneNumber));

        // check count before test
        assertThat(customerRepository.findById(customer.getId()))
                .hasValueSatisfying(c -> assertThat(c.getOrderCount()).isEqualTo(0));

        // place order for missing product (we don't have any product at this point)
        Map<String, Integer> itemQuantities = Collections.singletonMap("whatever", 1);
        assertThatThrownBy(
                () -> orderService.placeOrder(customerPhoneNumber, itemQuantities)
        ).isInstanceOf(ProductNotFoundException.class);

        // check count after test
        assertThat(customerRepository.findById(customer.getId()))
                .hasValueSatisfying(c -> assertThat(c.getOrderCount()).isEqualTo(1));
    }
}
