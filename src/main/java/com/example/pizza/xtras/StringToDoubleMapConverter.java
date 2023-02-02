package com.example.pizza.xtras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationPropertiesBinding
public class StringToDoubleMapConverter implements Converter<String, Map<String, Number>> {

    public static Logger LOG = LoggerFactory.getLogger(StringToDoubleMapConverter.class);

    @Override
    public Map<String, Number> convert(String from) {
        try {
            return (Map<String, Number>) new SpelExpressionParser().parseRaw(from).getValue(Map.class);
        } catch (EvaluationException | ParseException | ClassCastException e) {
            LOG.warn("Failed to convert input: " + e.getMessage());
            throw e;
        }
    }
}
