package com.matheus.api_auto_report;

import com.matheus.api_auto_report.infraestructure.persistence.migration.MigrationStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class ApiAutoReportApplication {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(ApiAutoReportApplication.class, args);


    }

    @PostConstruct
    public void runMigrations() throws SQLException {

        try(var connection = dataSource.getConnection()){
            new MigrationStrategy(dataSource).executeMigration();
        }
    }
}
