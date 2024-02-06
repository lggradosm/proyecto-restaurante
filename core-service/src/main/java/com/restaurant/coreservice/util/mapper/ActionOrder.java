package com.restaurant.coreservice.util.mapper;

import com.restaurant.coreservice.domain.model.Order;
import com.restaurant.coreservice.infraestructure.entity.OrdersEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ActionOrder {
    @Mapping(target = "idOrders", source = "idOrders")
    OrdersEntity fromOrderEntity(Order order);

    Order toOrder(OrdersEntity ordersEntity);

    List<Order> toOrders(Iterable<OrdersEntity> ordersEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateActionOrderEntityFromOrder(Order order, @MappingTarget OrdersEntity entity);
}
