package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.persistence.entity.UserEntity;
import com.matheus.api_auto_report.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor  // do Lombok
public class UserController {

    private final UserService service;

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) throws Exception {
        return service.insert(user);
    }

}

