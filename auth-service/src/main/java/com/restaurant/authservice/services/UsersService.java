package com.restaurant.authservice.services;

import com.restaurant.authservice.aggregates.request.RequestUserLogin;
import com.restaurant.authservice.aggregates.request.RequestUsers;
import com.restaurant.authservice.aggregates.response.ResponseBase;

public interface UsersService {
    ResponseBase getAllUsers();
    ResponseBase login(RequestUserLogin requestUserLogin);

    ResponseBase signin(RequestUsers requestUsers);
}
