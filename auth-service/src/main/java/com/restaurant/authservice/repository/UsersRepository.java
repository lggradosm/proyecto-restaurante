package com.restaurant.authservice.repository;

import com.restaurant.authservice.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {
    boolean existsByUsername(String username);
    UsersEntity findByUsername(String username);
}

