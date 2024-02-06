package com.restaurant.coreservice.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.coreservice.domain.model.Order;
import com.restaurant.coreservice.domain.model.OrderDetail;
import com.restaurant.coreservice.domain.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders_detail")
public class OrdersDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders_detail")
    private Integer idOrdersDetail;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id_orders")
    private OrdersEntity order;
    @ManyToOne
    @JoinColumn(name = "products_id_products")
    private ProductsEntity product;
    @Column(name = "unit_price")
    private Double unitPrice;
    private int amount;
    private Double subtotal;

//    public static OrdersDetailEntity fromDomainModel(OrderDetail orderDetail){
//        ProductsEntity productsEntity= ProductsEntity.fromDomainModel(orderDetail.getProduct());
////        OrdersEntity ordersEntity = OrdersEntity.fromDomainModel(orderDetail.getOrder());
//        OrdersEntity
//        return new OrdersDetailEntity(orderDetail.getIdOrdersDetail(),ordersEntity,productsEntity,orderDetail.getUnitPrice(),orderDetail.getAmount(),orderDetail.getSubtotal());
//    }
//
//    public OrderDetail toDomainModel(){
//        return  new OrderDetail(this.getIdOrdersDetail(),this.getOrder().toDomainModel(),this.getProduct().toDomainModel(), this.getUnitPrice(),this.getAmount(),this.getSubtotal());
//    }
}
