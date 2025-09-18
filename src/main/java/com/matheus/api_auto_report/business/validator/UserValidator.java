package com.matheus.api_auto_report.business.validator;

import com.matheus.api_auto_report.exception.exs.DuplicatedData;
import com.matheus.api_auto_report.exception.exs.NotFound;
import com.matheus.api_auto_report.infraestructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidator {

    private final UserRepository repository;

    public void verifyUniqueEmail(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new DuplicatedData("E-mail already registered");
        }
    }

    public void verifyUserExistsById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFound("User not found");
        }
    }

    public void verifyUserExistsByEmail(String email) {
        if (repository.findByEmail(email).isEmpty()) {
            throw new NotFound("User not found");
        }
    }


}
