package com.restaurant.authservice.util.validations;

import com.restaurant.authservice.aggregates.constant.Constants;
import com.restaurant.authservice.aggregates.request.RequestUsers;
import com.restaurant.authservice.repository.EmployeesRepository;
import com.restaurant.authservice.repository.RolesRepository;
import com.restaurant.authservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class
UsersValidations {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    EmployeesRepository employeesRepository;
    public boolean validateInput(RequestUsers requestUsers){
        if(requestUsers == null) return false;
        if(usersRepository.existsByUsername(requestUsers.getUsername())) return false;
        if(requestUsers.getDni().length() != Constants.DNI_LENGHT) return false;
        if(requestUsers.getRoleId() == 0 || !rolesRepository.existsById(requestUsers.getRoleId())) return false;
        if (isNullOrEmpty(requestUsers.getUsername()) || isNullOrEmpty(requestUsers.getPassword()) )
            return false;
        return true;
    }

    private boolean isNullOrEmpty(String data){
        return data == null || data.isEmpty();
    }
}
