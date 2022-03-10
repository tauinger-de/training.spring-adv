package com.example.pizza.product;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("default | product") // not enabled for "order" by design since we might want to hide public API
public class ProductRestController {

    //
    // --- constants ---
    //

    static final String ROOT = "/products";
    static final String GET_MANY_ENDPOINT = ROOT;
    static final String GET_ONE_ENDPOINT = ROOT + "/{productId}";

    //
    // --- injected beans ---
    //

    private final ProductService productService;

    //
    // --- constructors and setup ---
    //

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    //
    // --- REST endpoints ---
    //

    @GetMapping(GET_MANY_ENDPOINT)
    public Iterable<Product> getProducts() {
        return this.productService.getAllProducts();
    }


    @GetMapping(GET_ONE_ENDPOINT)
    public Product getProduct(@PathVariable String productId) {
        return this.productService.getProduct(productId);
    }

}
