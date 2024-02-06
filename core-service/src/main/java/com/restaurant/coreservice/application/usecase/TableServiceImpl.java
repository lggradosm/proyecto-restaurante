package com.restaurant.coreservice.application.usecase;

import com.restaurant.coreservice.aggregates.constant.Constants;
import com.restaurant.coreservice.aggregates.constant.HttpStatus;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.domain.model.Table;
import com.restaurant.coreservice.domain.ports.in.TableIn;
import com.restaurant.coreservice.domain.ports.out.TableOut;
import com.restaurant.coreservice.util.validation.TableValidations;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class TableServiceImpl implements TableIn {
    private final TableOut tableOut;
    private final TableValidations tableValidations;
    public TableServiceImpl(TableOut tableOut, TableValidations tableValidations) {
        this.tableOut = tableOut;
        this.tableValidations = tableValidations;
    }

    @Override
    public ResponseBase createTable(Table table) {
        boolean validation = tableValidations.validateInput(table);
        if(validation){
            table.setDateCreate(getTimestamp());
            System.out.println(getTimestamp());
            table.setStatus(Constants.ACTIVO);
            tableOut.createTable(table);
            return new ResponseBase(HttpStatus.OK,Constants.MESS_SUCCESS, Optional.of(table));
        }
        return new ResponseBase(HttpStatus.OK,Constants.MESS_ERROR_DATA_NOT_VALID,Optional.empty());
    }

    @Override
    public ResponseBase getTable(Integer id) {
        boolean exist = tableValidations.existTable(id);
        if(exist){
            Optional<Table> table = tableOut.getTable(id);
            if(table.isPresent()){
                return new ResponseBase(HttpStatus.OK,Constants.MESS_SUCCESS,table);
            }
            return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
        }
        return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
    }

    @Override
    public ResponseBase getTables() {
        List<Table> tableList = tableOut.getTables();
        return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS, Optional.of(tableList));
    }

    @Override
    public ResponseBase getActivesTables() {
        List<Table> tableList = tableOut.getActivesTables();
        return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS, Optional.of(tableList));
    }

    @Override
    public ResponseBase updateTable(Integer id, Table table) {
        boolean exist = tableValidations.existTable(id);
        if(exist){
            boolean validation = tableValidations.validateInput(table);
            if(validation) {
                tableOut.updateTable(id,table);
                return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS,Optional.of(table));
            }
            return new ResponseBase(HttpStatus.OK,Constants.MESS_ERROR_DATA_NOT_VALID,Optional.empty());
        }
        return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
    }

    @Override
    public ResponseBase deleteTable(Integer id) {
        boolean exist = tableValidations.existTable(id);
        if(exist){
            Optional<Table> table = tableOut.getTable(id);
            if(table.isPresent()){
                table.get().setDateDelete(getTimestamp());
                table.get().setStatus(Constants.INACTIVE);
                tableOut.deleteTable(table.get());
                return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS,Optional.of(tableValidations));
            }
            return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS,Optional.empty());
        }
        return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

}
