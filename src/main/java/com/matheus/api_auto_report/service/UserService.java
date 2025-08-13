package com.matheus.api_auto_report.service;

import com.matheus.api_auto_report.persistence.dao.UserDAO;
import com.matheus.api_auto_report.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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
}
