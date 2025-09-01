package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.dto.UserDTO;
import com.matheus.api_auto_report.persistence.entity.UserEntity;
import com.matheus.api_auto_report.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor  // do Lombok
public class UserController {

    private final UserService service;

    @PostMapping
    public UserEntity createUser(@RequestBody @Valid UserEntity user) throws Exception {
        return service.insert(user);
    }

    @GetMapping
    public Optional<UserDTO> getUser(@RequestParam int id) throws Exception {
        return service.findById(id);
    }

}

