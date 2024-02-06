package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.infraestructure.entity.OrdersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrdersDetailEntity,Integer> {
    @Query(value = "select * from orders_detail where id_orders_detail in (?)", nativeQuery = true)
    List<OrdersDetailEntity> findByOrderList(List<Integer> order);
    @Modifying
    @Query(value = "UPDATE  orders_detail SET orders_id_orders =  ?2 where id_orders_detail in (?1)", nativeQuery = true )
    int updateByOrderList(List<Integer> orderDetails, int orderId);
    @Query(value = "SELECT * FROM orders_detail where orders_id_orders = ?",nativeQuery = true)
    List<OrdersDetailEntity> findAllByOrder(Integer order);
}
