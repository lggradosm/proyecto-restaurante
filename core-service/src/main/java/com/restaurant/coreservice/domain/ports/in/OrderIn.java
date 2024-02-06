package com.restaurant.coreservice.domain.ports.in;

import com.restaurant.coreservice.aggregates.request.RequestOrder;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Order;

public interface OrderIn {
    ResponseBase createOrder(RequestOrder requestOrder);
    ResponseBase findOrder(Integer id);
}
