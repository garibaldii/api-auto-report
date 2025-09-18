package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.infraestructure.dto.UserRequestDTO;
import com.matheus.api_auto_report.infraestructure.dto.UserResponseDTO;
import com.matheus.api_auto_report.infraestructure.entity.UserEntity;
import com.matheus.api_auto_report.business.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
@RequiredArgsConstructor  // do Lombok
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO dto = service.findUserById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public UserEntity postUser(@Valid @RequestBody UserRequestDTO dto) {

        return service.saveUser(dto);

    }

    //notacao usada para atualizacao parcial de objeto
    @PatchMapping("/{email}")
    public void patchUser(@PathVariable String email, @RequestBody UserRequestDTO dto) {
        service.updateUserByEmail(email, dto);
    }

    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable String email) {
        service.deleteUserByEmail(email);
    }



}

