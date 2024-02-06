package com.restaurant.coreservice.domain.ports.in;

import com.restaurant.coreservice.aggregates.response.ResponseBase;

public interface EmployeeIn {
    ResponseBase getEmployee(Integer idEmployee);
}
