package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerRepository;
import com.example.pizza.product.Product;
import com.example.pizza.product.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(properties = {"app.order.daily-discounts={MONDAY:'10', TUESDAY:'10', WEDNESDAY:'10', THURSDAY:'10', FRIDAY:'10', SATURDAY:'10', SUNDAY:'10'}"})
class OrderRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setupTestData() {
        productRepository.save(new Product("p1", "Product One", 1.00));
        customerRepository.save(new Customer("Toni Test", null, "040-112233"));
    }

    @Test
    void sayHello() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(OrderRestController.GREETING_ENDPOINT))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.emptyString())));
    }

    @Test
    void placeOrder() throws Exception {
        OrderRequestData orderRequestData = new OrderRequestData();
        orderRequestData.phoneNumber = "040-112233";
        orderRequestData.itemQuantities = Collections.singletonMap("p1", 2);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(OrderRestController.PLACE_ORDER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(orderRequestData)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", Matchers.is(1.8)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.fullName", Matchers.is("Toni Test")));
    }

    private String toJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}