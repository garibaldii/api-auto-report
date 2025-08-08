package com.matheus.api_auto_report.persistence.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private final DataSourceConfig dataSourceConfig;

    public DatabaseConfig(DataSourceConfig dataSourceConfig){
        this.dataSourceConfig = dataSourceConfig;
    }

    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .url(dataSourceConfig.getUrl())
                .username(dataSourceConfig.getUsername())
                .password(dataSourceConfig.getPassword())
                .build();
    }
}
