package com.restaurant.authservice.util.mappers;

import com.restaurant.authservice.aggregates.constant.Constants;
import com.restaurant.authservice.aggregates.request.RequestUsers;
import com.restaurant.authservice.entity.EmployeesEntity;
import com.restaurant.authservice.entity.RolesEntity;
import com.restaurant.authservice.entity.UsersEntity;
import com.restaurant.authservice.repository.RolesRepository;
import com.restaurant.authservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    @Autowired
    private RolesRepository rolesRepository;
    public UsersEntity getUserEntityFromRequestUser(RequestUsers requestUsers){
        UsersEntity user = new UsersEntity();
        RolesEntity role = rolesRepository.findById(requestUsers.getRoleId()).get() ;
        if(role != null){
            EmployeesEntity employee = new EmployeesEntity();
            employee.setTelephone(requestUsers.getTelephone());
            employee.setStatus(Constants.ACTIVO);
            user.setUsername(requestUsers.getUsername());
            user.setPassword(requestUsers.getPassword());
            user.setRole(role);
            user.setEmployee(employee);
            user.setStatus(Constants.ACTIVO);
            return user;
        }
        return null;
    }
}
