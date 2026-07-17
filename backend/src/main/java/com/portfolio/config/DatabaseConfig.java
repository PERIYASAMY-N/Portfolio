package com.portfolio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DatabaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        System.out.println("=== DATABASE CONFIG DEBUG ===");
        System.out.println("Original DATABASE_URL: " + databaseUrl);
        
        // Convert Render's PostgreSQL URL format to JDBC format
        if (databaseUrl != null) {
            if (databaseUrl.startsWith("postgres://")) {
                databaseUrl = databaseUrl.replace("postgres://", "jdbc:postgresql://");
                System.out.println("Converted postgres:// to jdbc:postgresql://");
            } else if (databaseUrl.startsWith("postgresql://")) {
                databaseUrl = databaseUrl.replace("postgresql://", "jdbc:postgresql://");
                System.out.println("Converted postgresql:// to jdbc:postgresql://");
            }
        }
        
        System.out.println("Final JDBC URL: " + databaseUrl);
        System.out.println("=== END DATABASE CONFIG ===");
        
        return DataSourceBuilder
                .create()
                .url(databaseUrl)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
