package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.infraestructure.dto.UserLoginDTO;
import com.matheus.api_auto_report.infraestructure.dto.UserRequestDTO;
import com.matheus.api_auto_report.infraestructure.dto.UserResponseDTO;
import com.matheus.api_auto_report.infraestructure.entity.UserEntity;
import com.matheus.api_auto_report.business.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
        String token = service.authenticateUser(userLoginDTO);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO dto = service.findUserById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserRequestDTO dto) {
        UserEntity user = service.saveUser(dto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //notacao usada para atualizacao parcial de objeto
    @PatchMapping("/{email}")
    public ResponseEntity<UserResponseDTO> patchUser(@PathVariable String email, @RequestBody UserRequestDTO dto) {
        UserResponseDTO user = service.updateUserByEmail(email, dto);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("/{email}")
    public ResponseEntity deleteUser(@PathVariable String email) {
        service.deleteUserByEmail(email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

