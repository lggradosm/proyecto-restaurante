package com.restaurant.authservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name = "employees")
public class EmployeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employees")
    private Integer idEmployees;
    @Column(name = "name",nullable = false, length = 100)
    private String name;
    @Column(name = "lastname",nullable = false, length = 100)
    private String lastname;
    @Column(name = "dni",nullable = false, length = 15)
    private String dni;
    @Column(name = "telephone", length = 20)
    private String telephone;
    private int status;
}
