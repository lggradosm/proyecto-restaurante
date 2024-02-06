package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.infraestructure.entity.TablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TablesEntity,Integer> {
    List<TablesEntity> findAllActives();
    boolean existsByNumber(int number);
}
