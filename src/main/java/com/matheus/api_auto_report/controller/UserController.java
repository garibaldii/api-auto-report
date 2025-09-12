package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.infraestructure.entity.UserEntity;
import com.matheus.api_auto_report.business.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public List<UserDTO> getUsers() throws Exception {
        return service.getUsers();
    }

    @DeleteMapping
    public boolean deleteUser(@RequestParam  long id) throws Exception {
        return service.deleteById(id);
    }



}

