package com.restaurant.authservice.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserLogin {
    private String username;
    private String password;
}
