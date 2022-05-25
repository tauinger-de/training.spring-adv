package com.example.pizza.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    ObjectMapper objectMapper;

    private final String customerFullName = "Bill Gates";
    private final Address customerAddress = new Address("1415 L Street, Suite 200", "95814", "Sacramento, CA");
    private final String customerPhoneNumber = "+1 222 3333333";

    @Test
    void createCustomer() throws Exception {
        String responseJson = this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(CustomerRestController.CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullName\":\"Test Case\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // check persistence
        Integer customerId = JsonPath.read(responseJson, "$.id");
        Customer customer = this.customerRepository.findById(customerId.longValue()).orElse(null);
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Test Case", customer.getFullName());
    }

    /**
     * Validates a customer GET response using JSONAssert
     */
    @Test
    void getCustomer_variant1() throws Exception {
        // given
        Customer customer = new Customer(customerFullName, customerAddress, customerPhoneNumber);
        customer = customerRepository.save(customer);

        // when
        String actualJson = this.mockMvc
                .perform(MockMvcRequestBuilders.get(CustomerRestController.GET_ONE_ENDPOINT, customer.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        String expectedJson = toJson(customer);
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    /**
     * Validates a customer GET response using JsonPath
     */
    @Test
    void getCustomer_variant2() throws Exception {
        // given
        Customer customer = new Customer(customerFullName, customerAddress, customerPhoneNumber);
        customer = customerRepository.save(customer);

        // when & then
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(CustomerRestController.GET_ONE_ENDPOINT, customer.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(customer.getId()), Long.class)) // trick for asserting on Long!
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", Matchers.is(customerFullName)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.city", Matchers.is(customerAddress.getCity())));
    }

    private String toJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}
