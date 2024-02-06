package com.restaurant.coreservice.domain.ports.in;

import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.domain.model.Table;

public interface TableIn {
    ResponseBase createTable(Table table);
    ResponseBase getTable(Integer id);
    ResponseBase getTables();
    ResponseBase getActivesTables();
    ResponseBase updateTable(Integer id, Table table);
    ResponseBase deleteTable(Integer id);
}
