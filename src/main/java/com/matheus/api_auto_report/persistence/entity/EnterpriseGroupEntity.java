package com.matheus.api_auto_report.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class EnterpriseGroupEntity {

    @Id
    private Long id;
    private String name;
    private List<EnterpriseEntity> enterprises;
}
