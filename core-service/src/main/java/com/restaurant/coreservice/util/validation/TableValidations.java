package com.restaurant.coreservice.util.validation;

import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.domain.model.Table;
import com.restaurant.coreservice.domain.ports.out.TableOut;
import org.springframework.stereotype.Component;

@Component
public class TableValidations {
    private final TableOut tableOut;

    public TableValidations(TableOut tableOut) {
        this.tableOut = tableOut;
    }

    public boolean validateInput(Table table){
        if(table == null) return false;
        if(tableOut.existTable(table.getNumber())) return false;
        if(table.getNumber() ==0) return false;
        return true;
    }
    public boolean existTable(Integer id){
        return tableOut.existTable(id);
    }

}
