package com.restaurant.coreservice.domain.ports.out;

import com.restaurant.coreservice.domain.model.OrderDetail;

import java.util.List;

public interface OrderDetailOut {
    OrderDetail create(OrderDetail orderDetail);
    List<OrderDetail> findByOrderList(List<Integer> order);
    int updateByOrderList(List<Integer> order, int orderId);
    List<OrderDetail> findAllByOrder(Integer order);
}
