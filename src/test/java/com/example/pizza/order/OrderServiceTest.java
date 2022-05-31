package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerRepository;
import com.example.pizza.customer.CustomerSetup;
import com.example.pizza.product.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerSetup customerSetup;

    final String customerPhoneNumber = "123456789";

    /**
     * Make sure we have exactly the list of customers as provided by the CustomerSetup.
     */
    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        customerSetup.createCustomers();
    }

    /**
     * This test executes a simple order placement without any checks/assertions
     */
    @Test
    void placeOrder() {
        // given -- this data should exist due to CustomSetup and ProductSetup beans
        var existingCustomerPhoneNumber = "123-4567";
        var existingProductId = "S-01";

        // when
        orderService.placeOrder(
                existingCustomerPhoneNumber,
                Map.of(existingProductId, 1));
    }

    /**
     * Tests that the order-count is increased although the order process exists with an exception.
     */
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
