package com.restaurant.coreservice.application.service;

import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.domain.ports.in.ProductIn;

public class ProductService implements ProductIn {

    private final ProductIn productIn;

    public ProductService(ProductIn productIn) {
        this.productIn = productIn;
    }


    @Override
    public ResponseBase createProduct(Product product) {
        return productIn.createProduct(product);
    }

    @Override
    public ResponseBase getProduct(Integer id) {
        return productIn.getProduct(id);
    }

    @Override
    public ResponseBase getProducts() {
        return productIn.getProducts();
    }

    @Override
    public ResponseBase getActivesProducts() {
        return productIn.getActivesProducts();
    }

    @Override
    public ResponseBase updateProduct(Integer id, Product product) {
        return productIn.updateProduct(id,product);
    }

    @Override
    public ResponseBase deleteProduct(Integer id) {
        return productIn.deleteProduct(id);
    }
}
