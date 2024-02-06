package com.restaurant.coreservice.application.service;

import com.restaurant.coreservice.aggregates.request.RequestOrder;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Order;
import com.restaurant.coreservice.domain.ports.in.OrderIn;

public class OrderService implements OrderIn {
    private final OrderIn orderIn;

    public OrderService(OrderIn orderIn) {
        this.orderIn = orderIn;
    }

    @Override
    public ResponseBase createOrder(RequestOrder requestOrder) {
        return orderIn.createOrder(requestOrder);
    }

    @Override
    public ResponseBase findOrder(Integer id) {
        return orderIn.findOrder(id);
    }
}
