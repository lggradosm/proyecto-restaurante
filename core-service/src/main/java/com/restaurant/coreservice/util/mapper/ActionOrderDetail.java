package com.restaurant.coreservice.util.mapper;

import com.restaurant.coreservice.domain.model.Order;
import com.restaurant.coreservice.domain.model.OrderDetail;
import com.restaurant.coreservice.infraestructure.entity.OrdersDetailEntity;
import com.restaurant.coreservice.infraestructure.entity.OrdersEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ActionOrderDetail {
    @Mapping(target = "idOrdersDetail", source = "idOrdersDetail")
    @Mapping(target = "order",ignore = true)
    OrdersDetailEntity fromOrderDetailEntity(OrderDetail order);
    @Mapping(target = "order", source = "order")
    OrderDetail toOrderDetail(OrdersDetailEntity ordersEntity);
    List<OrderDetail> toOrderDetails(Iterable<OrdersDetailEntity> ordersEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateActionOrderDetailEntityFromOrderDetail(OrderDetail orderDetail, @MappingTarget OrdersDetailEntity entity);
}
