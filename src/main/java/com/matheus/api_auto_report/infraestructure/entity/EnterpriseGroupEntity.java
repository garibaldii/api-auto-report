package com.matheus.api_auto_report.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "ENTERPRISE_GROUP")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnterpriseGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnterpriseEntity> enterprises;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
