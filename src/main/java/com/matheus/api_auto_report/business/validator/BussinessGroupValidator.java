package com.matheus.api_auto_report.business.validator;

import com.matheus.api_auto_report.exception.exs.NotFound;
import com.matheus.api_auto_report.infraestructure.repositories.BussinessGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BussinessGroupValidator {

    private final BussinessGroupRepository repository;


    public void verifyGroupExistsById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFound("Group not found");
        }
    }

}
