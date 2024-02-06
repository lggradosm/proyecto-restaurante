package com.restaurant.coreservice.util.validation;

import com.restaurant.coreservice.aggregates.request.RequestOrder;
import com.restaurant.coreservice.aggregates.request.RequestOrderItem;
import com.restaurant.coreservice.domain.ports.out.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderValidations {
    private final EmployeeOut employeeOut;
    private final TableOut tableOut;
    private final ProductOut productOut;

    private final OrderOut orderOut;

    public OrderValidations(EmployeeOut employeeOut, TableOut tableOut, ProductOut productOut, OrderOut orderOut) {
        this.employeeOut = employeeOut;
        this.productOut = productOut;
        this.tableOut = tableOut;
        this.orderOut = orderOut;
    }

    public boolean inputValidation(RequestOrder requestOrder){
        if(!existProduct(requestOrder.getOrderItems())) return false;
        return true;
    }

    private boolean existProduct(List<RequestOrderItem> productList){
        boolean exist = false;
        for (RequestOrderItem item: productList){
            if(productOut.existProduct(item.getIdProduct())) exist = true;
            else exist = false;
        }
        return exist;
    }

    public boolean existOrder(Integer id) {
        return  orderOut.existOrder(id);
    }
}
