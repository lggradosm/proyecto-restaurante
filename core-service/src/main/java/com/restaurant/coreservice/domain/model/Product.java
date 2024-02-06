package com.restaurant.coreservice.domain.model;

import com.restaurant.coreservice.domain.model.common.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Audit {
    private Integer idProducts;
    private String name;
    private String description;
    private Double price;
    private int stockable;
    private int status;

    public Product(Integer idProducts,String name,String description ,  Double price , int stockable, int status, String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif, String userDelete, Timestamp dateDelete) {
        super(userCreate, dateCreate, userModif, dateModif, userDelete, dateDelete);
        this.idProducts = idProducts;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockable = stockable;
        this.status = status;
    }
}
