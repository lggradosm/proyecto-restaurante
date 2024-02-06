package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.domain.model.Employee;
import com.restaurant.coreservice.infraestructure.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
}
