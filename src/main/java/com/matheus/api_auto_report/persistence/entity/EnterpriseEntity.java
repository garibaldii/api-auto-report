package com.matheus.api_auto_report.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

@Data
@Entity
public class EnterpriseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cnpj;
    private String cellphoneNumber;

    @ManyToOne
    @JoinColumn(name = "enterprise_group_id")
    private EnterpriseGroupEntity group;

    @OneToMany(mappedBy = "enterprise")
    private List<SheetEntity> sheets;

}