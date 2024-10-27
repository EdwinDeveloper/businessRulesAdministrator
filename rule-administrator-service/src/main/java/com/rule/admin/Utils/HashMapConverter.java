package com.rule.admin.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final Logger logger = LoggerFactory.getLogger(HashMapConverter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> customerInfo) {
        try {
            return objectMapper.writeValueAsString(customerInfo);
        } catch (JsonProcessingException e) {
            logger.error("Error converting map to JSON string: {}", e.getMessage(), e);
            return "{}";
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {
        try {
            return objectMapper.readValue(customerInfoJSON, Map.class);
        } catch (IOException e) {
            logger.error("Error converting JSON string to map: {}", e.getMessage(), e);
            return new HashMap<>();
        }
    }
}
