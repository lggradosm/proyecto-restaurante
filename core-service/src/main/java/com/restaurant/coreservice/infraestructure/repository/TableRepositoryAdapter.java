package com.restaurant.coreservice.infraestructure.repository;

import com.restaurant.coreservice.domain.model.Table;
import com.restaurant.coreservice.domain.ports.out.TableOut;
import com.restaurant.coreservice.infraestructure.entity.TablesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TableRepositoryAdapter implements TableOut {
    @Autowired
    private TableRepository tableRepository;
    @Override
    public Table createTable(Table table) {
        TablesEntity tablesEntity = TablesEntity.fromDomainModel(table);
        return tableRepository.save(tablesEntity).toDomainModel();
    }

    @Override
    public Optional<Table> getTable(Integer id) {
        return tableRepository.findById(id).map(TablesEntity::toDomainModel);
    }

    @Override
    public List<Table> getTables() {
        return tableRepository.findAll().stream().map(TablesEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public List<Table> getActivesTables() {
        return tableRepository.findAllActives().stream().map(TablesEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Table> updateTable(Integer id, Table table) {
        TablesEntity tablesEntity = TablesEntity.fromDomainModel(table);
        return Optional.of(tableRepository.save(tablesEntity).toDomainModel());
    }

    @Override
    public boolean deleteTable(Table table) {
        TablesEntity tablesEntity = TablesEntity.fromDomainModel(table);
        tableRepository.save(tablesEntity);
        return true;
    }

    @Override
    public boolean existTable(int number) {
        return tableRepository.existsByNumber(number);
    }
}
