package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.domain.model.OrderDetail;
import com.restaurant.coreservice.domain.ports.out.OrderDetailOut;
import com.restaurant.coreservice.infraestructure.entity.OrdersDetailEntity;
import com.restaurant.coreservice.util.mapper.ActionOrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailRepositoryAdapter implements OrderDetailOut {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ActionOrderDetail actionOrderDetail;
    @Transactional
    @Override
    public OrderDetail create(OrderDetail orderDetail) {
//        OrdersDetailEntity ordersDetailEntity = OrdersDetailEntity.fromDomainModel(orderDetail);
//        return orderDetailRepository.save(ordersDetailEntity).toDomainModel();
        OrdersDetailEntity ordersDetailEntity = actionOrderDetail.fromOrderDetailEntity(orderDetail);
        OrderDetail orderDetail1 = actionOrderDetail.toOrderDetail(orderDetailRepository.save(ordersDetailEntity));
        return orderDetail1;
    }

    @Override
    public List<OrderDetail> findByOrderList(List<Integer> order) {
        return actionOrderDetail.toOrderDetails(orderDetailRepository.findByOrderList(order));
//         return orderDetailRepository.findByOrder(order).map(list -> actionOrderDetail.toOrderDetails(list)).orElse(new ArrayList<>());
    }

    @Transactional
    @Override
    public int updateByOrderList(List<Integer> order, int orderId) {
        return orderDetailRepository.updateByOrderList(order, orderId);
    }

    @Override
    public List<OrderDetail> findAllByOrder(Integer order) {
        return actionOrderDetail.toOrderDetails(orderDetailRepository.findAllByOrder(order));
    }


}
