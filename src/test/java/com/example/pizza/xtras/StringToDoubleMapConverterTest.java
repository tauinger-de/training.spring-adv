package com.example.pizza.xtras;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringToDoubleMapConverterTest {

    @Test
    void convert() {
        // when
        var map = new StringToDoubleMapConverter().convert("{'MONDAY':0, 'TUESDAY':100}");

        // then
        assertThat(map).hasSize(2);
        assertThat(map).containsKey("MONDAY");
        assertThat(map.get("MONDAY")).isEqualTo(0);
        assertThat(map).containsKey("TUESDAY");
        assertThat(map.get("TUESDAY")).isEqualTo(100);
    }

//    @Test
//    void convert_fails() {
//        // when
//        var map = new StringToDoubleMapConverter().convert("asdasdasdas");
//
//        // then
//        assertThat(map).isNotNull();
//        assertThat(map).hasSize(0);
//    }
}