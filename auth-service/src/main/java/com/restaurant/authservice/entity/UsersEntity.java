package com.restaurant.authservice.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Setter
@Getter
@Table(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")
    private Integer idUsers;
    @Column(name = "username",nullable = false,length = 50)
    private String username;
    @Column(name = "password",nullable = false,length = 250)
    private String password;
    private int passwordChanged;
    private int status;
    @OneToOne
    @JoinColumn(
        name = "roles_id_roles"
    )
    private RolesEntity role;
    @OneToOne
    @JoinColumn(
            name = "employees_id_employees"
    )
    private EmployeesEntity employee;
}
