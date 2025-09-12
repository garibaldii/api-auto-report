package com.matheus.api_auto_report.infraestructure.dto;
import com.matheus.api_auto_report.infraestructure.entity.EnterpriseGroupEntity;

import java.util.List;

public record UserRequestDTO(String name, String email, String password, List<EnterpriseGroupEntity> groups) {
}
