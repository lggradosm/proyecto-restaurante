package com.restaurant.coreservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.infraestructure.entity.ProductsEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class Util {
    public static String convertToJsonEntity(List<Product> productsEntity) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(productsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static <T> T convertFromJson(String json, Class<T> valueType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
