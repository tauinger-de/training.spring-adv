package com.example.pizza.xtras;

import com.example.pizza.product.ProductService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HasProductsHealthIndicator implements HealthIndicator {

    private final ProductService productService;

    public HasProductsHealthIndicator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Health health() {
        var allProducts = productService.getAllProducts();
        if (allProducts == null || !allProducts.iterator().hasNext()) {
            return Health.down()
                    .withDetail("error", "No products available")
                    .build();
        }
        var builder = Health.up();
        long productCount = allProducts.spliterator().getExactSizeIfKnown();
        if (productCount != -1) {
            builder.withDetail("info", "Got " + productCount + " products available for ordering");
        }
        return builder.build();
    }
}
