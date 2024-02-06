package com.restaurant.coreservice.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOrderItem {
    private Integer idProduct;
    private Double unitPrice;
    private int amount;
}
