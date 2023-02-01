package com.example.pizza.order;

import com.example.pizza.customer.Customer;
import com.example.pizza.customer.CustomerRepository;
import com.example.pizza.customer.CustomerService;
import com.example.pizza.product.Product;
import com.example.pizza.product.ProductRepository;
import com.example.pizza.product.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
@Import({OrderService.class, CustomerService.class, ProductService.class})
@AutoConfigureDataJpa
@EnableJpaRepositories(basePackageClasses = {CustomerRepository.class, ProductRepository.class, OrderRepository.class})
@TestPropertySource(properties = {"app.order.daily-discounts={MONDAY:'10', TUESDAY:'10', WEDNESDAY:'10', THURSDAY:'10', FRIDAY:'10', SATURDAY:'10', SUNDAY:'10'}"})
@Transactional // todo required due to @BeforeEach setup, but why exactly?
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
        // when
        var resultActions = this.mockMvc
                .perform(MockMvcRequestBuilders.get(OrderRestController.GREETING_ENDPOINT));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.emptyString())));
    }

    @Test
    void placeOrder() throws Exception {
        // given
        IncomingOrderDto incomingOrderDto = new IncomingOrderDto();
        incomingOrderDto.phoneNumber = "040-112233";
        incomingOrderDto.itemQuantities = Collections.singletonMap("p1", 2);

        // when
        var resultActions = this.mockMvc.perform(post(OrderRestController.PLACE_ORDER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(incomingOrderDto))
        );

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalPrice", Matchers.is(1.8)))
                .andExpect(jsonPath("$.customer.fullName", Matchers.is("Toni Test")));
    }

    private String toJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}