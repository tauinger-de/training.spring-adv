package com.example.pizza.product;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.List;

public class ProductRequestDataCsvConverter
        extends AbstractHttpMessageConverter<List<ProductRequestData>> {


    public ProductRequestDataCsvConverter() {
        super(MediaType.valueOf("text/csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected List<ProductRequestData> readInternal(
            Class<? extends List<ProductRequestData>> clazz,
            HttpInputMessage inputMessage
    ) throws IOException, HttpMessageNotReadableException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(ProductRequestData.class)
                .withHeader()
                .withStrictHeaders(true)
                .withColumnSeparator(',');
        return csvMapper.readerFor(ProductRequestData.class)
                .with(schema)
                .<ProductRequestData>readValues(inputMessage.getBody())
                .readAll();
    }

    @Override
    protected void writeInternal(List<ProductRequestData> products, HttpOutputMessage outputMessage) throws HttpMessageNotWritableException {
        throw new UnsupportedOperationException();
    }
}
