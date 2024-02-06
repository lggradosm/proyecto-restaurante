package com.restaurant.authservice.repository;

import com.restaurant.authservice.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<EmployeesEntity,Integer> {

}
