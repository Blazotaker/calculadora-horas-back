package com.alejandro.project.configuration;

import com.alejandro.project.configuration.domain.DataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfig {
    @Bean
    public DataSource postgresDataSource(DataSourceProperties properties) {
        String url = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", properties.getHost(), properties.getPort(), properties.getDb());
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(properties.getUsername());
        //config.setPassword(properties.getPassword());
        return new HikariDataSource(config);
    }


}
