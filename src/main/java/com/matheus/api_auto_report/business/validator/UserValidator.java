package com.matheus.api_auto_report.business.validator;

import com.matheus.api_auto_report.exception.exs.DuplicateData;
import com.matheus.api_auto_report.exception.exs.NotFound;
import com.matheus.api_auto_report.infraestructure.dto.UserRequestDTO;
import com.matheus.api_auto_report.infraestructure.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository repository;

    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }

    public void verifyUniqueEmail(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new DuplicateData("E-mail already registered");
        }
    }

    public void verifyUserExistsById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFound("User not found");
        }
    }

    public void verifyUserExistsByEmail(String email) {
        if (repository.findByEmail(email).isEmpty()) {
            throw new NotFound("User not found");
        }
    }


}
