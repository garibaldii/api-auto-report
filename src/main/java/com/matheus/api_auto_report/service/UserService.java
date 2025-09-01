package com.matheus.api_auto_report.service;

import com.matheus.api_auto_report.dto.UserDTO;
import com.matheus.api_auto_report.persistence.dao.UserDAO;
import com.matheus.api_auto_report.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final DataSource dataSource;

    private final PasswordEncoder encoder;

    public UserEntity insert(UserEntity entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);

            String hashedPassword = encoder.encode(entity.getPassword());
            entity.setPassword(hashedPassword);
            dao.insert(entity);

            connection.commit();

            return entity;
        } catch (SQLException e) {
            connection.rollback();
            connection.close();
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<UserDTO> findById(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        UserDAO dao = new UserDAO(connection);
        var optional = dao.find(id);

        if(optional.isPresent()){
            UserDTO user = optional.get();
            return Optional.of(user);
        }

        return Optional.empty();


    }
}
