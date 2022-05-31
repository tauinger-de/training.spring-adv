package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerApiClient;
import com.example.pizza.product.Product;
import com.example.pizza.product.ProductApiClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = { "app.order.daily-discounts={DUMMY:'0'}"})
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    CustomerApiClient customerApiClient;

    @MockBean
    ProductApiClient productApiClient;

    final String customerPhoneNumber = "123456789";

    /**
     * Mocks both product and customer API clients and checks that we can place an order including correct amount.
     */
    @Test
    void placeOrder() {
        // given
        Product prd1 = new Product("p1", "Some Product", 100.0);
        Product prd2 = new Product("p2", "Another Product", 1000.0);
        Mockito.when(productApiClient.findById(prd1.getProductId())).thenReturn(prd1);
        Mockito.when(productApiClient.findById(prd2.getProductId())).thenReturn(prd2);

        Customer customer = new Customer(12345, "Tony Test", null, customerPhoneNumber);
        Mockito.when(customerApiClient.findMany(Map.of("phoneNumber", customerPhoneNumber))).thenReturn(List.of(customer));

        // when
        var order = orderService.placeOrder(
                customerPhoneNumber,
                Map.of(prd1.getProductId(), 1, prd2.getProductId(), 2));

        // then
        assertThat(order).isNotNull();
        assertThat(order.getTotalPrice()).isEqualTo(2100.0);
    }
}
