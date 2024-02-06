package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.domain.model.Employee;
import com.restaurant.coreservice.domain.ports.out.EmployeeOut;
import com.restaurant.coreservice.infraestructure.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeRepositoryAdapter implements EmployeeOut {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Optional<Employee> getEmployee(Integer idEmployee) {
        return employeeRepository.findById(idEmployee).map(EmployeeEntity::toDomainModel);
    }
}
