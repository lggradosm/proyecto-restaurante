package com.restaurant.coreservice.domain.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer idEmployees;
    private String name;
    private String lastname;
    private String dni;
    private String telephone;
    private int status;
}
