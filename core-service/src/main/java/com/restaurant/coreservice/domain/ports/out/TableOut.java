package com.restaurant.coreservice.domain.ports.out;

import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Table;

import java.util.List;
import java.util.Optional;

public interface TableOut {

    Table createTable(Table table);
    Optional<Table> getTable(Integer id);
    List<Table> getTables();
    List<Table> getActivesTables();
    Optional<Table> updateTable(Integer id, Table table);
    boolean deleteTable(Table table);
    boolean existTable(int number);
}
