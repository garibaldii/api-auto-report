package com.matheus.api_auto_report.business.service;

import com.matheus.api_auto_report.business.validator.UserValidator;
import com.matheus.api_auto_report.config.security.JwtTokenService;
import com.matheus.api_auto_report.config.security.UserDetailsImpl;
import com.matheus.api_auto_report.infraestructure.dto.UserLoginDTO;
import com.matheus.api_auto_report.infraestructure.dto.UserRequestDTO;
import com.matheus.api_auto_report.infraestructure.dto.UserResponseDTO;
import com.matheus.api_auto_report.infraestructure.repositories.UserRepository;

import com.matheus.api_auto_report.infraestructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;


    private final UserRepository repository;
    private final UserValidator validator;
    private final PasswordEncoder encoder;

    //retorna o token jwt
    public String authenticateUser(UserLoginDTO userLoginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password());

        //autentica o usuário com as credenciais fornecidas
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //obtém o objeto userDetails do usuário  autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        //retorna o token jwt
        return  jwtTokenService.generateToken(userDetails);
    }


    public UserEntity saveUser(UserRequestDTO dto) {

        //validadores de propriedade do objeto, tal como obrigatorietade, formato e tamanho, são
        //declarados diretamente no dto, seguindo o padrão do mercado.
        validator.verifyUniqueEmail(dto.email());

        String hashedPassword = encoder.encode(dto.password());

        UserEntity entity = UserEntity.builder()
                .name(dto.name())
                .email(dto.email())
                .password(hashedPassword)
                .groups(new ArrayList<>())
                .build();
        //repository.saveAndFlush(entity) vai retornar o usuário salvo e entao fechar a conexão com o banco de dados, e aqui estamos dizendo caso eele existir, retornará... Uma forma de esquivar de possíveis resultados nulos.
        return repository.saveAndFlush(entity);
    }

    public UserResponseDTO findUserById(long id) {

        validator.verifyUserExistsById(id);

        //neste caso, mesmo validando a possibilidade do usuário nao existir, a IDE ainda alerta
        //de que poderá ser lançado um possível nulo, entao declaramos o orElseThrow();
        UserEntity user = repository.findById(id).orElseThrow();

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    public UserResponseDTO findUserByEmail(String email) {

        validator.verifyUserExistsByEmail(email);

        //neste caso, mesmo validando a possibilidade do usuário nao existir, a IDE ainda alerta
        //de que poderá ser lançado um possível nulo, entao declaramos o orElseThrow();
        UserEntity user = repository.findByEmail(email).orElseThrow();

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }


    public UserResponseDTO updateUserByEmail(String email, UserRequestDTO requestDTO) {

        validator.verifyUserExistsByEmail(email);

        UserEntity user = repository.findByEmail(email).orElseThrow();


        if(requestDTO.name() != null) user.setName(requestDTO.name());


        if (requestDTO.password() != null) {
            String hashedPassword = encoder.encode(requestDTO.password());
            user.setPassword(hashedPassword);
        }


        repository.save(user);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        validator.verifyUserExistsByEmail(email);

        repository.deleteByEmail(email);
    }


}
