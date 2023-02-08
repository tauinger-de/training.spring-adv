package com.example.pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestDataCsvConverterTest {

    @Test
    void readInternal() throws IOException {
        // given
        var csv = "productId,name,price\na,b,1";
        var inputStream = new ByteArrayInputStream(csv.getBytes());
        var mockedMessage = Mockito.mock(HttpInputMessage.class);
        Mockito.when(mockedMessage.getBody()).thenReturn(inputStream);

        // when
        var dataList = new ProductRequestDataCsvConverter().readInternal(
                null,
                mockedMessage
        );

        // then
        Assertions.assertThat(dataList).isNotNull();
        Assertions.assertThat(dataList).hasSize(1);
        Assertions.assertThat(dataList.get(0).getProductId()).isEqualTo("a");
        Assertions.assertThat(dataList.get(0).getName()).isEqualTo("b");
        Assertions.assertThat(dataList.get(0).getPrice()).isEqualTo(1);
    }
}