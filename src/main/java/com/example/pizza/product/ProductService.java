package com.example.pizza.product;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    //
    // injected beans
    //

    private final ProductRepository productRepository;

    //
    // constructors and setup
    //

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //
    // business logic
    //

    public Iterable<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProduct(String productId) {
        return this.productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("For productId `" + productId + "`"));
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

    public Product createProduct(Product product) {
        if (this.productRepository.existsById(product.productId)) {
            throw new IllegalStateException("Persistence already contains a product with id: " + product.productId);
        }
        return this.productRepository.save(product);
    }
}
