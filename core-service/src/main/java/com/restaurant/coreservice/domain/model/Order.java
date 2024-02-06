package com.restaurant.coreservice.domain.model;

import com.restaurant.coreservice.domain.model.common.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Audit {

    private Integer idOrders;
    private Table table;
    private Employee employee;
    private Double subtotal;
    private Double total;
    private int status;
    private String name;
    private String lastname;
    private String dni;
    private String businesName;
    private String tradeName;
    private String ruc;
    private List<OrderDetail> orderDetails;

    public Order(String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif, String userDelete, Timestamp dateDelete, Integer idOrders, Table table, Employee employee, Double subtotal, Double total, int status,String name,String lastname,String dni,String businessName, String tradeName,String ruc, List<OrderDetail> orderDetails) {
        super(userCreate, dateCreate, userModif, dateModif, userDelete, dateDelete);
        this.idOrders = idOrders;
        this.table = table;
        this.employee = employee;
        this.subtotal = subtotal;
        this.total = total;
        this.status = status;
        this.name= name;
        this.lastname = lastname;
        this.dni = dni;
        this.businesName = businessName;
        this.tradeName = tradeName;
        this.ruc = ruc;
        this.orderDetails = orderDetails;
    }
}
