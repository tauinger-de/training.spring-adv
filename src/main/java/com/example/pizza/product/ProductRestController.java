package com.example.pizza.product;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Profile("default | product")
// not enabled for "order" by design since we might want to hide public API
public class ProductRestController {

    //
    // --- constants ---
    //

    static final String ROOT = "/products";
    static final String GET_MANY_ENDPOINT = ROOT;
    static final String UPLOAD_CSV_ENDPOINT = ROOT;

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

    @PutMapping(value = UPLOAD_CSV_ENDPOINT, consumes = "text/csv")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadProducts(@RequestBody List<ProductRequestData> productDataList) {
        // we could also use a Converter and ConversionService here if this conversion is required
        // in other code places
        var products = productDataList.stream()
                .map(d -> new Product(d.getProductId(), d.getName(), d.getPrice()))
                .collect(Collectors.toList());
        this.productService.replaceAllProducts(products);
    }
}
