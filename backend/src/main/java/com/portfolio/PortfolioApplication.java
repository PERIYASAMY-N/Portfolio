package com.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioApplication {
    public static void main(String[] args) {
        System.out.println("=== APPLICATION STARTUP ===");
        System.out.println("Active Profile: " + System.getenv("SPRING_PROFILES_ACTIVE"));
        System.out.println("DATABASE_URL exists: " + (System.getenv("DATABASE_URL") != null));
        System.out.println("PORT: " + System.getenv("PORT"));
        System.out.println("=== STARTING APPLICATION ===");
        
        SpringApplication.run(PortfolioApplication.class, args);
    }
}
