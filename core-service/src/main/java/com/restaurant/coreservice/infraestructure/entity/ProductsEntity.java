package com.restaurant.coreservice.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.infraestructure.entity.common.Audit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NamedQuery(name = "ProductsEntity.findAllActives", query = "select p from ProductsEntity p where p.status=1")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_products")
    private Integer idProducts;
    @Column(name = "name",length = 50)
    private String name;
    @Column(name = "description", length = 250)
    private String description;
    @Column(name = "price")
    private Double price;
    private int stockable;
    private int status;


    public static ProductsEntity fromDomainModel(Product product){
        ProductsEntity productsEntity = new ProductsEntity(product.getIdProducts(),product.getName(),product.getDescription(),product.getPrice(),product.getStockable(),product.getStatus());
        productsEntity.setUserCreate(product.getUserCreate());
        productsEntity.setDateCreate(product.getDateCreate());
        productsEntity.setUserModif(product.getUserModif());
        productsEntity.setDateModif(product.getDateModif());
        productsEntity.setUserDelete(product.getUserDelete());
        productsEntity.setDateDelete(product.getDateDelete());
        return productsEntity;
    }

    public Product toDomainModel(){
        return new Product(this.getIdProducts(),this.getName(),this.getDescription(),this.getPrice(),this.getStockable(),this.getStatus(),this.getUserCreate(),this.getDateCreate(),this.getUserModif(),this.getDateModif(),this.getUserDelete(),this.getDateDelete());
    }
}
