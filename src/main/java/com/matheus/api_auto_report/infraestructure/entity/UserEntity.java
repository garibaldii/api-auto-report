package com.matheus.api_auto_report.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field can not be blank")
    @Size(max = 50, message = "Name can not be longer than 50 characters")
    private String name;

    @NotBlank(message = "Field can not be blank")
    @Email(message = "E-mail invalid")
    private String email;

    @NotBlank(message = "Field can not be blank")
    @Size(max = 244, message = "Password can not be longer than 244 characters")
    @Column(name = "hashed_password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnterpriseGroupEntity> groups;
}
