package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.aggregates.response.ResponseReniec;
import com.restaurant.coreservice.domain.model.Order;
import com.restaurant.coreservice.domain.ports.out.OrderOut;
import com.restaurant.coreservice.infraestructure.entity.OrdersEntity;
import com.restaurant.coreservice.util.mapper.ActionOrder;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderOut {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ActionOrder actionOrder;
    @Override
    public Order createOrder(Order order) {
//        OrdersEntity ordersEntity = OrdersEntity.fromDomainModel(order);
//        return orderRepository.save(ordersEntity).toDomainModel();
        OrdersEntity ordersEntity = actionOrder.fromOrderEntity(order);
        Order order1 = actionOrder.toOrder(orderRepository.save(ordersEntity));
        return order1;
    }


    @Override
    public Optional<Order> findOrder(Integer id) {
        return Optional.of(actionOrder.toOrder(orderRepository.findById(id).get()));
    }

    @Override
    public boolean existOrder(Integer id) {
        return orderRepository.existsById(id);
    }
}
