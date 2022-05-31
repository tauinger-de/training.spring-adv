package com.example.pizza.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer someCustomer;

    /**
     * Remove all customers from persistence. Prepare an in-memory instance for testing.
     */
    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        this.someCustomer = new Customer(
                "C. S. Test",
                new Address("22 Coastal Drive", "2231-A", "Mockington"),
                "+64 2 1233-12321"
        );
    }

    @Test
    public void getAllCustomers() {
        // check if none exist
        Iterable<Customer> allCustomers = customerService.getAllCustomers();
        Assertions.assertFalse(allCustomers.iterator().hasNext());

        // test data
        customerService.createCustomer(this.someCustomer);

        // exec
        allCustomers = customerService.getAllCustomers();

        // checks
        Assertions.assertTrue(allCustomers.iterator().hasNext());

        Customer customer = allCustomers.iterator().next();
        Assertions.assertEquals(this.someCustomer.getFullName(), customer.getFullName());
        Assertions.assertEquals(this.someCustomer.getAddress().getStreet(), customer.getAddress().getStreet());
        Assertions.assertEquals(this.someCustomer.getAddress().getPostalCode(), customer.getAddress().getPostalCode());
        Assertions.assertEquals(this.someCustomer.getAddress().getCity(), customer.getAddress().getCity());
        Assertions.assertEquals(this.someCustomer.getPhoneNumber(), customer.getPhoneNumber());
    }

}
