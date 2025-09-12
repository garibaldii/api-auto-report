package com.matheus.api_auto_report.business.service;

import com.matheus.api_auto_report.infraestructure.dto.UserRequestDTO;
import com.matheus.api_auto_report.infraestructure.dto.UserResponseDTO;
import com.matheus.api_auto_report.infraestructure.repositories.UserRepository;

import com.matheus.api_auto_report.infraestructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public Optional<UserEntity> saveUser(UserRequestDTO dto) {

        String hashedPassword = encoder.encode(dto.password());

        UserEntity entity = UserEntity.builder()
                .name(dto.name())
                .email(dto.email())
                .password(hashedPassword)
                .build();
        //repository.saveAndFlush(entity) vai retornar o usuário salvo e entao fechar a conexão com o banco de dados, e aqui estamos dizendo caso eele existir, retornará... Uma forma de esquivar de possíveis resultados nulos.
        return Optional.of(repository.saveAndFlush(entity));
    }

    public UserResponseDTO findUserById(long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }


    public UserResponseDTO findUserByEmail(String email) {
        UserEntity user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    public UserResponseDTO updateUserByEmail(String email, UserRequestDTO requestDTO) {

        UserResponseDTO responseDTO = findUserByEmail(email);



    }

    @Transactional
    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }


}
