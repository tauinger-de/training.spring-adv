package com.example.pizza.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.function.Predicate;

@RestController
@Profile("default | customer") // not enabled for "order" by design since we might want to hide public API
public class CustomerRestController {

    //
    // --- constants ---
    //

    private static final String ROOT = "/customers";
    public static final String GET_ONE_ENDPOINT = ROOT + "/{id}";
    public static final String GET_MANY_ENDPOINT = ROOT;
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

    @Operation(summary = "Retrieve a single customer", description = "Retrieve a single customer identified by it's numeric id")
    @GetMapping(value = GET_ONE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@PathVariable long id) {
        return this.customerService.getCustomer(id);
    }


    @Operation(summary = "Retrieve multiple customers", description = "Retrieves all customers, if no filters are set. Otherwise the list of filtered customers")
    @GetMapping(value = GET_MANY_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Customer> getCustomers(
            @Parameter(description = "Optional filter to include only customers in the response whose phone number contains the given string")
            @RequestParam(required = false) String phoneNumber
    ) {
        var filters = new ArrayList<Predicate<Customer>>();
        if (StringUtils.isNotBlank(phoneNumber)) {
            filters.add(c -> StringUtils.contains(c.getPhoneNumber(), phoneNumber));
        }
        return this.customerService.getCustomers(filters);
    }


    @Operation(summary = "Create customer", description = "Creates a new customer by storing it in persistence. This will auto-generate a numeric customer id")
    @PostMapping(value = CREATE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody CustomerInDto customerInDto) {
        return this.customerService.createCustomer(
                this.conversionService.convert(customerInDto, Customer.class)
        );
    }

}
