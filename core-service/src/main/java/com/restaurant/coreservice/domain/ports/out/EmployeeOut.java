package com.restaurant.coreservice.domain.ports.out;

import com.restaurant.coreservice.domain.model.Employee;

import java.util.Optional;

public interface EmployeeOut {
    Optional<Employee> getEmployee(Integer idEmployee);
}
