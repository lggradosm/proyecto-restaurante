package com.restaurant.coreservice.domain.ports.out;

import com.restaurant.coreservice.aggregates.response.ResponseReniec;
import com.restaurant.coreservice.domain.model.Order;

import java.util.Optional;

public interface OrderOut {
    Order createOrder(Order order);
    Optional<Order> findOrder(Integer id);
    boolean existOrder(Integer id);
}
