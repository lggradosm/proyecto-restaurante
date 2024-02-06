package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.infraestructure.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrdersEntity,Integer> {
}
