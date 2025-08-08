package com.matheus.api_auto_report.persistence.repository;

import com.matheus.api_auto_report.persistence.entity.UserEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.StatementImpl;

public class UserRepository {

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public UserEntity insert(final UserEntity entity) throws SQLException{
        var sql = "INSERT INTO USERS (name, email, hashed_password) values (?, ?, ?)";
        try(PreparedStatement statement = dataSource.getConnection().prepareStatement(sql)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());

            statement.executeUpdate();

            if(statement instanceof StatementImpl impl){
                entity.setId(impl.getLastInsertID());
            }
        }

        return entity;
    }
}
