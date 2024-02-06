package com.restaurant.coreservice.application.service;

import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Table;
import com.restaurant.coreservice.domain.ports.in.TableIn;

public class TableService implements TableIn {
    private final TableIn tableIn;

    public TableService(TableIn tableIn) {
        this.tableIn = tableIn;
    }

    @Override
    public ResponseBase createTable(Table table) {
        return tableIn.createTable(table);
    }

    @Override
    public ResponseBase getTable(Integer id) {
        return tableIn.getTable(id);
    }

    @Override
    public ResponseBase getTables() {
        return tableIn.getTables();
    }

    @Override
    public ResponseBase getActivesTables() {
        return tableIn.getActivesTables();
    }

    @Override
    public ResponseBase updateTable(Integer id, Table table) {
        return tableIn.updateTable(id,table);
    }

    @Override
    public ResponseBase deleteTable(Integer id) {
        return tableIn.deleteTable(id);
    }
}
