package com.restaurant.coreservice.infraestructure.entity;

import com.restaurant.coreservice.domain.model.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employees")
    private Integer idEmployees;
    @Column(name = "name",length = 100)
    private String name;
    @Column(name = "lastname",length = 100)
    private String lastname;
    @Column(name = "dni",length = 15)
    private String dni;
    @Column(name = "telephone",length = 20)
    private String telephone;

    private int status;

    public static EmployeeEntity fromDomainModel(Employee employee){
        return new EmployeeEntity(employee.getIdEmployees(),employee.getName(),employee.getLastname(),employee.getDni(),employee.getTelephone(),employee.getStatus());
    }

    public Employee toDomainModel(){
        return new Employee(this.getIdEmployees(),this.getName(),this.getLastname(),this.getDni(),this.getTelephone(),this.getStatus());
    }
}
