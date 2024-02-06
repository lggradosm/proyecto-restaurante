package com.restaurant.authservice.config.security;

import com.restaurant.authservice.entity.UsersEntity;
import com.restaurant.authservice.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    private UsersEntity user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = usersRepository.findByUsername(username);
        if(!Objects.isNull(user)){
            return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("Usuario desconocido");
        }
    }

    public UsersEntity getUser(){
        return user;
    }
}
