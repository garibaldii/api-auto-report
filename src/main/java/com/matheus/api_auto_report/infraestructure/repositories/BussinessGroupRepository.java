package com.matheus.api_auto_report.infraestructure.repositories;

import com.matheus.api_auto_report.infraestructure.entity.BussinessGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BussinessGroupRepository extends JpaRepository<BussinessGroupEntity, Long> {
    List<BussinessGroupEntity> findAllByUser_Id(Long userId);

    Optional<BussinessGroupEntity> findByIdAndUser_Id(Long groupId, Long userId);
}
