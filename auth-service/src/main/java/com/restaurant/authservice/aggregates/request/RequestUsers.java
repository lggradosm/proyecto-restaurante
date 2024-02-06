package com.restaurant.authservice.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUsers {
    private String username;
    private String password;
    private int roleId;
    private String dni;
    private String telephone;
}
