package com.example.pizza.xtras;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationPropertiesBinding
public class StringToDoubleMapConverter implements Converter<String, Map<String,Double>> {

    @Override
    public Map<String,Double> convert(String from) {
        Map<String,Double> result = new HashMap<>();
        from = StringUtils.trimLeadingCharacter(from, '{');
        from = StringUtils.trimTrailingCharacter(from, '}');
        String[] data = from.split(",");
        for (String entry : data) {
            String[] keyValue = entry.split(":");
            if (keyValue.length == 2) {
                String doubleStr = keyValue[1];
                doubleStr = StringUtils.trimLeadingCharacter(doubleStr, '\'');
                doubleStr = StringUtils.trimTrailingCharacter(doubleStr, '\'');
                result.put(keyValue[0], Double.parseDouble(doubleStr));
            }
        }
        return result;
    }
}
