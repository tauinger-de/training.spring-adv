package com.example.pizza.product;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

// todo add other fields
class ProductAssertions extends AbstractAssert<ProductAssertions, Product> {

    private ProductAssertions(Product actual) {
        super(actual, ProductAssertions.class);
    }

    public static ProductAssertions assertProduct(Product actual) {
        assertThat(actual).isNotNull();
        return new ProductAssertions(actual);
    }

    public ProductAssertions hasProductId(String expected) {
        assertThat(actual.productId).isEqualTo(expected);
        return this;
    }
}
