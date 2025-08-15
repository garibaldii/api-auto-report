package com.matheus.api_auto_report.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Data
@Entity
public class EnterpriseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @CNPJ
    @NotBlank
    private String cnpj;

    @NotBlank
    @UniqueElements
    @Size(max = 20)
    private String cellphoneNumber;

    @ManyToOne
    @JoinColumn(name = "enterprise_group_id")
    private EnterpriseGroupEntity group;

    @OneToMany(mappedBy = "enterprise")
    private List<SheetEntity> sheets;

}