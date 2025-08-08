package com.matheus.api_auto_report.persistence.entity;

import jakarta.persistence.Id;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class EnterpriseEntity {

    @Id
    private Long id;
    private String name;
    private String cnpj;
    private String cellphoneNumber;
    private List<SheetEntity> SaleSpreadsheets;

}