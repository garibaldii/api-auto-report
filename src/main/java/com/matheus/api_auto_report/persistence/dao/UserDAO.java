package com.matheus.api_auto_report.persistence.dao;

import com.matheus.api_auto_report.dto.UserDTO;
import com.matheus.api_auto_report.persistence.entity.UserEntity;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

import com.mysql.cj.jdbc.StatementImpl;
import org.apache.catalina.User;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection){
        this.connection = connection;
    }

    public UserEntity insert(final UserEntity entity) throws SQLException {
        var sql = "INSERT INTO USERS (name, email, hashed_password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());

            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getLong(1)); // Coluna 1 = id gerado
                }
            }
        }

        return entity;
    }

    public Optional<UserDTO> find(final int id) throws SQLException {
        var sql = "SELECT id, email FROM USERS WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                UserDTO user = new UserDTO(resultSet.getLong("id"), resultSet.getString("email"));

                return Optional.of(user);
            }

            return Optional.empty();
        }
    }

}
