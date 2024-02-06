package com.restaurant.authservice.controller;

import com.restaurant.authservice.aggregates.request.RequestUserLogin;
import com.restaurant.authservice.aggregates.request.RequestUsers;
import com.restaurant.authservice.aggregates.response.ResponseBase;
import com.restaurant.authservice.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseBase login(@RequestBody RequestUserLogin requestUserLogin){
        return usersService.login(requestUserLogin);
    }

    @PostMapping("/signup")
    public ResponseBase signup(@RequestBody RequestUsers requestUsers) {
        return usersService.signin(requestUsers);
    }

    @GetMapping()
    public ResponseBase getAll(){
        return usersService.getAllUsers();
    }
}
