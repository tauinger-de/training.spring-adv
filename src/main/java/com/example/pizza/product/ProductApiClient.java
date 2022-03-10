package com.example.pizza.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-api", url = "${app.product.api.root-url}")
public interface ProductApiClient {

    @GetMapping(value = "/products")
    List<Product> findAll();

    @GetMapping(value = "/products/{productId}")
    Product findById(@PathVariable("productId") String productId);

}
