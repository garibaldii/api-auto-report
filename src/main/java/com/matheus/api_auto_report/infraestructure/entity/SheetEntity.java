package com.matheus.api_auto_report.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "SHEETS")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
