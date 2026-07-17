package com.portfolio.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(databaseUrl);
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(5);
        
        return new HikariDataSource(config);
    }
}
