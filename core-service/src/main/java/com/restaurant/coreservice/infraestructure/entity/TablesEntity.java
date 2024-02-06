package com.restaurant.coreservice.infraestructure.entity;

import com.restaurant.coreservice.infraestructure.entity.common.Audit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tables")
@NamedQuery(name = "TablesEntity.findAllActives",query = "select t from TablesEntity t where t.status = 1")
public class TablesEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tables")
    private Integer idTables;
    @Column(name = "number",unique = true)
    private int number;
    private int status;

    public static TablesEntity fromDomainModel(com.restaurant.coreservice.domain.model.Table table){
        TablesEntity tablesEntity = new TablesEntity(table.getIdTables(),table.getNumber(),table.getStatus());
        tablesEntity.setUserCreate(table.getUserCreate());
        tablesEntity.setDateCreate(table.getDateCreate());
        tablesEntity.setUserModif(table.getUserModif());
        tablesEntity.setDateModif(table.getDateModif());
        tablesEntity.setUserDelete(table.getUserDelete());
        tablesEntity.setDateDelete(table.getDateDelete());
        return tablesEntity;
    }

    public com.restaurant.coreservice.domain.model.Table toDomainModel(){
        return new com.restaurant.coreservice.domain.model.Table(this.getIdTables(),this.getNumber(),this.getStatus(),this.getUserCreate(),this.getDateCreate(),this.getUserModif(),this.getDateModif(),this.getUserDelete(),this.getDateDelete());
    }
}
