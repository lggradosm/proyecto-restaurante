package com.restaurant.coreservice.domain.model;

import com.restaurant.coreservice.domain.model.common.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Table extends Audit {
    private Integer idTables;
    private int number;
    private int status;

    public Table( Integer idTables,int number, int status,String userCreate, Timestamp dateCreate, String userModif, Timestamp dateModif, String userDelete, Timestamp dateDelete) {
        super(userCreate, dateCreate, userModif, dateModif, userDelete, dateDelete);
        this.idTables = idTables;
        this.number = number;
        this.status = status;
    }
}
