package com.example.pizza.customer;

import org.springframework.core.convert.ConversionService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("default | customer") // not enabled for "order" by design since we might want to hide public API
public class CustomerRestController {

    //
    // --- constants ---
    //

    private static final String ROOT = "/customers";
    public static final String GET_ONE_ENDPOINT = ROOT + "/{id}";
    public static final String GET_ALL_ENDPOINT = ROOT;
    public static final String CREATE_ENDPOINT = ROOT;

    //
    // --- injected beans ---
    //

    private final CustomerService customerService;

    private final ConversionService conversionService;

    //
    // --- constructors and setup ---
    //

    public CustomerRestController(CustomerService customerService, ConversionService conversionService) {
        this.customerService = customerService;
        this.conversionService = conversionService;
    }

    //
    // --- REST endpoints ---
    //

    @GetMapping(GET_ONE_ENDPOINT)
    public Customer getCustomer(@PathVariable long id) {
        return this.customerService.getCustomer(id);
    }


    @GetMapping(GET_ALL_ENDPOINT)
    public Iterable<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    @PostMapping(CREATE_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody CreateCustomerRequestData createCustomerRequestData) {
        return this.customerService.createCustomer(
                this.conversionService.convert(createCustomerRequestData, Customer.class)
        );
    }

}
