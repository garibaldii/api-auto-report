package com.matheus.api_auto_report.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class SheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date monthDate;

    private int sumSubTotal;
    private int sumTaxes;
    private int finalTransfer;

    @Lob
    private byte[] file;

    @ManyToOne()
    @JoinColumn(name = "enterprise_id")
    private EnterpriseEntity enterprise;
}
