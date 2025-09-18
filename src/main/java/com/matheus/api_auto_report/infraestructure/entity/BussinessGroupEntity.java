package com.matheus.api_auto_report.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Table(name = "bussiness_groups")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BussinessGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bussiness Group name can not be blank")
    @Size(max = 50)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnterpriseEntity> enterprises;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
