package org.example.support.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    private static final String HIKARI_PROPERTIES = "spring.datasource.hikari";

    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(new HikariDataSource(mainHikariConfig()));
    }

    @Bean
    @ConfigurationProperties(prefix = HIKARI_PROPERTIES)
    public HikariConfig mainHikariConfig() {
        return new HikariConfig();
    }
}
