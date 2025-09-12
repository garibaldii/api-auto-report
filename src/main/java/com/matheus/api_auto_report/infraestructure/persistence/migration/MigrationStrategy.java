package com.matheus.api_auto_report.infraestructure.persistence.migration;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class MigrationStrategy {

    private final DataSource dataSource;

    public void executeMigration() {
        var originalOut = System.out;
        var originalErr = System.err;

        try (var fos = new FileOutputStream("liquibase.log");
             var logStream = new PrintStream(fos)) {

            System.setOut(logStream);
            System.setErr(logStream);

            try (
                    var connection = dataSource.getConnection();
                    var jdbcConnection = new JdbcConnection(connection);
            ) {
                var liquibase = new Liquibase(
                        "db/changelog/db.changelog-master.yaml",
                        new ClassLoaderResourceAccessor(),
                        jdbcConnection);
                liquibase.update();
            }

        } catch (SQLException | LiquibaseException | IOException e) {
            e.printStackTrace(originalErr);
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }

}
