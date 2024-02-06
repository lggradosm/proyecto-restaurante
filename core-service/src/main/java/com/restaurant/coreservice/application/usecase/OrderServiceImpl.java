package com.restaurant.coreservice.application.usecase;

import com.restaurant.coreservice.aggregates.constant.Constants;
import com.restaurant.coreservice.aggregates.constant.HttpStatus;
import com.restaurant.coreservice.aggregates.request.RequestOrder;
import com.restaurant.coreservice.aggregates.request.RequestOrderItem;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.aggregates.response.ResponseReniec;
import com.restaurant.coreservice.aggregates.response.ResponseSunat;
import com.restaurant.coreservice.domain.model.*;
import com.restaurant.coreservice.domain.ports.in.EmployeeIn;
import com.restaurant.coreservice.domain.ports.in.OrderIn;
import com.restaurant.coreservice.domain.ports.in.ProductIn;
import com.restaurant.coreservice.domain.ports.out.*;
import com.restaurant.coreservice.infraestructure.entity.EmployeeEntity;
import com.restaurant.coreservice.infraestructure.entity.OrdersEntity;
import com.restaurant.coreservice.infraestructure.feignClient.ReniecClient;
import com.restaurant.coreservice.infraestructure.feignClient.SunatClient;
import com.restaurant.coreservice.util.validation.OrderValidations;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderIn {
    private final OrderOut orderOut;
    private final EmployeeOut employeeOut;
    private final OrderDetailOut orderDetailOut;
    private final OrderValidations orderValidations;
    private final ProductOut productOut;
    private SunatClient sunatClient;
    private ReniecClient reniecClient;
    private final TableOut tableOut;

    private final String tokenApi = "apis-token-7253.-hkxgtUPJUMpZf973m0Evq3BMrqsITRz";
    public OrderServiceImpl(OrderOut orderOut,EmployeeOut employeeOut, OrderDetailOut orderDetailOut, OrderValidations orderValidations, ProductOut productOut, TableOut tableOut, SunatClient sunatClient, ReniecClient reniecClient) {
        this.orderOut = orderOut;
        this.employeeOut = employeeOut;
        this.orderDetailOut = orderDetailOut;
        this.orderValidations = orderValidations;
        this.productOut = productOut;
        this.tableOut = tableOut;
        this.sunatClient = sunatClient;
        this.reniecClient = reniecClient;
    }

    @Override
    public ResponseBase createOrder(RequestOrder requestOrder) {
        boolean verify = orderValidations.inputValidation(requestOrder);
        if(verify) {
            Order order = getOrder(requestOrder);
//            Order order1 = orderOut.createOrder(order);
//            List<OrderDetail> orderDetailList = getOrdersDetail(requestOrder);
//            List<OrderDetail> list = new ArrayList<>();
//            for(OrderDetail orderDetail: orderDetailList){
//                orderDetail.setOrder(order1);
//                System.out.println(orderDetail.getOrder().getIdOrders());
//                list.add(orderDetailOut.create(orderDetail));
//            }

            List<OrderDetail> orderDetailList = getOrdersDetail(requestOrder);
            List<Integer> orderList = new ArrayList<>();

            order.setOrderDetails(orderDetailList);
            if (orderDetailList != null) {
                Order order1 = orderOut.createOrder(order);
                for (OrderDetail orderDetail : order1.getOrderDetails()) {
                    orderList.add(orderDetail.getIdOrdersDetail());
                }
                if (orderDetailOut.updateByOrderList(orderList, order1.getIdOrders()) != 0) {
                    orderDetailList = orderDetailOut.findByOrderList(orderList);
                    for (OrderDetail orderDetail : orderDetailList) {
                        orderDetail.setOrder(order1);
                    }
                }
                return new ResponseBase(HttpStatus.CREATED, "", Optional.of(orderDetailList));
            }
            return new ResponseBase(HttpStatus.NOT_FOUND, Constants.MESS_ERROR_DATA_NOT_VALID, Optional.empty());

        }
        return new ResponseBase(HttpStatus.NOT_FOUND, Constants.MESS_ERROR_DATA_NOT_VALID, Optional.empty());

    }

    @Override
    public ResponseBase findOrder(Integer id) {
        boolean exist = orderValidations.existOrder(id);
        if(exist){
            Optional<Order> order = orderOut.findOrder(id);
            List<OrderDetail> list = orderDetailOut.findAllByOrder(id);
            order.get().setOrderDetails(list);
            return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_VALID, Optional.of(order.get()));

        }else{
            return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND, Optional.empty());
        }
    }



    private Order getOrder(RequestOrder requestOrder){
        Optional<Employee> employee = employeeOut.getEmployee(requestOrder.getIdEmployee());
        Optional<Table> table = tableOut.getTable(requestOrder.getIdTable());
        if(table.isPresent() && employee.isPresent()){
            Order order = new Order();
            order.setEmployee(employee.get());
            order.setTable(table.get());
            Double subtotal = 0.0;
            order.setSubtotal(subtotal);
            order.setTotal(subtotal);
            order.setStatus(Constants.ACTIVO);
            order.setDateCreate(getTimestamp());
            if(requestOrder.getDni()!= null && requestOrder.getDni() != ""  ) {
                ResponseReniec responseReniec = getExecutionReniec(requestOrder.getDni());
                order.setDni(responseReniec.getNumeroDocumento());
                order.setName(responseReniec.getNombres());
                order.setLastname(responseReniec.getApellidoPaterno()+" "+responseReniec.getApellidoMaterno());
            }
            if(requestOrder.getRuc()!= null && requestOrder.getRuc() != ""){
                ResponseSunat responseSunat = getExecutionSunat(requestOrder.getRuc());
                order.setRuc(responseSunat.getNumeroDocumento());
                order.setBusinesName(responseSunat.getRazonSocial());
                order.setTradeName(responseSunat.getRazonSocial());
            }
            return order;
        }
        return null;
    }

    private List<OrderDetail> getOrdersDetail(RequestOrder requestOrder){
        List<OrderDetail> list = new ArrayList<>();
        for (RequestOrderItem item: requestOrder.getOrderItems()){
            OrderDetail orderDetail = new OrderDetail();
            Optional<Product> product = productOut.getProduct(item.getIdProduct());
            if(product.isPresent()){
                orderDetail.setProduct(product.get());
                orderDetail.setAmount(item.getAmount());
                orderDetail.setUnitPrice(item.getUnitPrice()==null?product.get().getPrice(): item.getUnitPrice());
                orderDetail.setSubtotal(orderDetail.getUnitPrice()*orderDetail.getAmount());
                list.add(orderDetail);
            }
        }
        return list;
    }

    private Timestamp getTimestamp(){
        long timestamp = System.currentTimeMillis();
        return new Timestamp(timestamp);
    }

    private ResponseSunat getExecutionSunat (String numero) {
        String authorization = "Bearer "+tokenApi ;
        ResponseSunat responseSunat = sunatClient.getInfoSunat(numero, authorization);
        return responseSunat;
    }

    private ResponseReniec getExecutionReniec (String numero) {
        String authorization = "Bearer "+tokenApi ;
        ResponseReniec responseReniec = reniecClient.getInfoReniec(numero, authorization);
        return responseReniec;
    }


}
