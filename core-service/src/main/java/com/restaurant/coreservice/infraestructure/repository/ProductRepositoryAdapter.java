package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.aggregates.constant.Constants;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.domain.ports.out.ProductOut;
import com.restaurant.coreservice.infraestructure.entity.ProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductOut {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        ProductsEntity productsEntity = ProductsEntity.fromDomainModel(product);
        return productRepository.save(productsEntity).toDomainModel();
    }

    @Override
    public Optional<Product> getProduct(Integer id) {
        return productRepository.findById(id).map(ProductsEntity::toDomainModel);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll().stream().map(ProductsEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public List<Product> getActivesProducts() {
        return productRepository.findAllActives().stream().map(ProductsEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> updateProduct(Integer id, Product product) {
        ProductsEntity productsEntity = ProductsEntity.fromDomainModel(product);
        return Optional.of(productRepository.save(productsEntity).toDomainModel());
    }

    @Override
    public boolean deleteProduct(Product product) {
        ProductsEntity productsEntity = ProductsEntity.fromDomainModel(product);
        productRepository.save(productsEntity);
        return true;
    }

    @Override
    public boolean existProduct(Integer id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean existProductByName(String name) {
        return productRepository.existsByName(name);
    }


}
