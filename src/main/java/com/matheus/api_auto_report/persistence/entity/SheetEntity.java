package com.matheus.api_auto_report.persistence.entity;

import jakarta.persistence.Id;

import java.util.Date;

public class SheetEntity {

    @Id
    private long id;
    private Date monthDate;

    private int sumSubTotal;
    private int sumTaxes;
    private int finalTransfer;

    private byte[] file;

}
