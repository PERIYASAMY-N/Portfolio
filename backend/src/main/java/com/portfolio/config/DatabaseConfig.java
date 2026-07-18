package com.portfolio.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "DATABASE_URL")
    public DataSource dataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        System.out.println("=== DATABASE CONFIG STARTING ===");
        System.out.println("DATABASE_URL found: " + (databaseUrl != null));
        
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            System.out.println("ERROR: DATABASE_URL is not set!");
            throw new RuntimeException("DATABASE_URL environment variable is not set!");
        }
        
        try {
            URI dbUri = new URI(databaseUrl);
            
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String host = dbUri.getHost();
            int port = dbUri.getPort();
            String dbName = dbUri.getPath().substring(1); // Remove leading "/"
            
            String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s", host, port, dbName);
            
            System.out.println("=== DATABASE CONFIG DEBUG ===");
            System.out.println("Original URL: " + databaseUrl);
            System.out.println("Parsed JDBC URL: " + jdbcUrl);
            System.out.println("Host: " + host);
            System.out.println("Port: " + port);
            System.out.println("Database: " + dbName);
            System.out.println("Username: " + username);
            System.out.println("=== END DATABASE CONFIG ===");
            
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName("org.postgresql.Driver");
            config.setMaximumPoolSize(5);
            config.setConnectionTimeout(30000);
            config.setIdleTimeout(600000);
            config.setMaxLifetime(1800000);
            
            return new HikariDataSource(config);
            
        } catch (URISyntaxException e) {
            System.out.println("ERROR: Failed to parse DATABASE_URL: " + e.getMessage());
            throw new RuntimeException("Failed to parse DATABASE_URL: " + e.getMessage(), e);
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected error: " + e.getMessage());
            throw new RuntimeException("Failed to configure database: " + e.getMessage(), e);
        }
    }
}
