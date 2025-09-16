package com.matheus.api_auto_report.infraestructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserRequestDTO(

        @NotBlank(message = "Name can ot be blank")
        @Size(max = 50, message = "Name cannot be longer than 50 characters")
        String name,

        @NotBlank(message = "Email can not be blank")
        @Email(message = "E-mail invalid")
        String email,

        @NotBlank(message = "Password can not be blank")
        @Size(max = 244, message = "Password can not be longer than 244 characters")
        String password)
{}
