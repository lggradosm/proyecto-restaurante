package com.restaurant.coreservice.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.restaurant.coreservice.domain.model.Employee;
import com.restaurant.coreservice.domain.model.Order;
import com.restaurant.coreservice.domain.model.OrderDetail;
import com.restaurant.coreservice.infraestructure.entity.common.Audit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrdersEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders")
    private Integer idOrders;
    @ManyToOne
    @JoinColumn(name = "tables_id_tables", nullable = false)
    private TablesEntity table;
    @ManyToOne
    @JoinColumn(name = "employees_id_employees",nullable = false)
    private EmployeeEntity employee;
    private Double subtotal;
    private Double total;
    private int status;
    @Column(name = "name",length = 100)
    private String name;
    @Column(name = "lastname",length = 100)
    private String lastname;
    @Column(name = "dni",length = 8)
    private String dni;
    @Column(name = "business_name",length = 100)
    private String businnesName;
    @Column(name = "trade_name",length = 100)
    private String tradeName;
    @Column(name = "ruc",length = 11)
    private String ruc;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrdersDetailEntity> orderDetails;

//    public static OrdersEntity fromDomainModel(Order order){
//        TablesEntity tablesEntity = TablesEntity.fromDomainModel(order.getTable());
//        EmployeeEntity employeeEntity = EmployeeEntity.fromDomainModel(order.getEmployee());
//        List<OrdersDetailEntity> ordersDetailEntities = order.getOrderDetails().stream().map(OrdersDetailEntity::fromDomainModel).collect(Collectors.toList());
//        OrdersEntity ordersEntity = new OrdersEntity(order.getIdOrders(),tablesEntity,employeeEntity,order.getSubtotal(),order.getTotal(),order.getStatus(),order.getName(),order.getLastname(),order.getDni(),order.getBusinesName(),order.getTradeName(),order.getRuc(),ordersDetailEntities);
//        ordersEntity.setUserCreate(order.getUserCreate());
//        ordersEntity.setDateCreate(order.getDateCreate());
//        ordersEntity.setUserModif(order.getUserModif());
//        ordersEntity.setDateModif(order.getDateModif());
//        ordersEntity.setUserDelete(order.getUserDelete());
//        ordersEntity.setDateDelete(order.getDateDelete());
//        return ordersEntity;
//    }
//
//    public Order toDomainModel(){
//        com.restaurant.coreservice.domain.model.Table table = this.getTable().toDomainModel();
//        Employee employee = this.getEmployee().toDomainModel();
//        List<OrderDetail> orderDetailList = this.getOrderDetails().stream().map(OrdersDetailEntity::toDomainModel).collect(Collectors.toList());
//        return new Order(this.getUserCreate(), this.getDateCreate(), this.getUserModif(), this.getDateModif(),  this.getUserDelete(), this.getDateDelete(), this.getIdOrders(), table, employee, this.getSubtotal(), this.getTotal(), this.getStatus(),this.getName(),this.getLastname(),this.getDni(),this.getBusinnesName(),this.getTradeName(),this.getRuc(),orderDetailList);
//    }

}
