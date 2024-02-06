package com.restaurant.coreservice.domain.ports.out;

import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductOut {
    Product createProduct(Product product);
    Optional<Product> getProduct(Integer id);
    List<Product> getProducts();
    List<Product>  getActivesProducts();

    Optional<Product> updateProduct(Integer id, Product product);
    boolean deleteProduct(Product product);

    boolean existProduct(Integer id);
    boolean existProductByName(String name);
}
