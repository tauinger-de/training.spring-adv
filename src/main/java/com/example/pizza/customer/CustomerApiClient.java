package com.example.pizza.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "customer-api", url = "${app.customer.api.root-url}")
public interface CustomerApiClient {

    @GetMapping(value = "/customers/{customerId}")
    Customer findById(@PathVariable("customerId") long customerId);

    @GetMapping(value = "/customers")
    List<Customer> findMany(@SpringQueryMap Map<String, String> filterParams);

}
