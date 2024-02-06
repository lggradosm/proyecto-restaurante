package com.restaurant.coreservice.domain.ports.in;

import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Product;
import org.apache.coyote.Response;

import java.util.List;
import java.util.Optional;

public interface ProductIn {
    ResponseBase createProduct(Product product);
    ResponseBase getProduct(Integer id);
    ResponseBase getProducts();
    ResponseBase getActivesProducts();
    ResponseBase updateProduct(Integer id, Product product);
    ResponseBase deleteProduct(Integer id);
}
