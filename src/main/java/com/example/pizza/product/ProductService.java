package com.example.pizza.product;

import feign.FeignException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("default | product | order")
public class ProductService {

    //
    // injected beans
    //

    private final ProductApiClient productApiClient;

    //
    // constructors and setup
    //

    public ProductService(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    //
    // business logic
    //

    public Product getProduct(String productId) {
        try {
            return this.productApiClient.findById(productId);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ProductNotFoundException("Product with id " + productId + " not found");
            }
            else {
                throw e;
            }
        }
    }

    public Double getTotalPrice(Map<String, Integer> productQuantities) {
        // loop over each map entry (which is productId -> quantity) and map each entry to the product's price
        // multiplied by desired quantity. Then sum all up and that is our total.
        return productQuantities.entrySet().stream()
                .mapToDouble(entry -> {
                    Product product = getProduct(entry.getKey());
                    return product.price * entry.getValue();
                })
                .sum();
    }

}
