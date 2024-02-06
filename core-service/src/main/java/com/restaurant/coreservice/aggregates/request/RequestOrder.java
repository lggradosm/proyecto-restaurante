package com.restaurant.coreservice.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrder {
    private Integer idTable;
    private Integer idEmployee;
    private List<RequestOrderItem>orderItems;
    private String dni;
    private String ruc;
}
