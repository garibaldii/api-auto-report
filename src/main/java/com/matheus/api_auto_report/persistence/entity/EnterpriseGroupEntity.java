package com.matheus.api_auto_report.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class EnterpriseGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnterpriseEntity> enterprises;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
