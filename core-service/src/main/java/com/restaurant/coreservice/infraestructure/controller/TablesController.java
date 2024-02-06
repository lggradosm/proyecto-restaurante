package com.restaurant.coreservice.infraestructure.controller;

import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.application.service.TableService;
import com.restaurant.coreservice.domain.model.Table;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/core/tables")

public class TablesController {
    private final TableService tableService;

    public TablesController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping()
    public ResponseBase getAll(){
        return tableService.getTables();
    }
    @GetMapping("/actives")
    public ResponseBase getAllActives(){
        return tableService.getActivesTables();
    }

    @GetMapping("/{tableId}")
    public ResponseBase getProduct(@PathVariable Integer tableId){
        return tableService.getTable(tableId);
    }

    @PostMapping()
    public ResponseBase createProduct(@RequestBody Table table){
        return tableService.createTable(table);
    }

    @PutMapping("/{tableId}")
    public ResponseBase updateProduct(@PathVariable Integer tableId, @RequestBody Table table){
        return tableService.updateTable(tableId,table);
    }

    @DeleteMapping("/{tableId}")
    public ResponseBase deleteProduct(@PathVariable Integer tableId) {
        return tableService.deleteTable(tableId);
    }
}
