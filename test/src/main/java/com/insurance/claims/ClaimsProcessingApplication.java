package com.insurance.claims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class for Claims Processing System
 * Uses Spring Boot 3.0 and Java 17
 */
@SpringBootApplication
public class ClaimsProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaimsProcessingApplication.class, args);
        System.out.println("==============================================");
        System.out.println("Claims Processing System Started Successfully!");
        System.out.println("==============================================");
    }
}

