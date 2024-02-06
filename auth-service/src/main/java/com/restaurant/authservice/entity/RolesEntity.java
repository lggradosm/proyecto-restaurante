package com.restaurant.authservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class RolesEntity {
    @Id
    @GeneratedValue
    @Column(name = "id_roles")
    private Integer idRoles;
    @Column(name = "name",nullable = false,length = 25,unique = true)
    private String name;
    private int status;
}
