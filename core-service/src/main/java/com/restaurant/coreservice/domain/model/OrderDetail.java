package com.restaurant.coreservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Integer idOrdersDetail;
    private Order order;
    private Product product;
    private Double unitPrice;
    private int amount;
    private Double subtotal;
}
