package com.matheus.api_auto_report.infraestructure.repositories;

import com.matheus.api_auto_report.infraestructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    void deleteByEmail(String email);
}
