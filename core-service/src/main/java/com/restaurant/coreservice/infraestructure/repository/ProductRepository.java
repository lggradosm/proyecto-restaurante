package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.infraestructure.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity,Integer> {
    boolean existsByName(String name);
    List<ProductsEntity> findAllActives();
}
