package com.insurance.claims.config;

import com.insurance.claims.model.User;
import com.insurance.claims.model.UserRole;
import com.insurance.claims.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Initializes sample data when application starts
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final UserRepository userRepository;
    
    @Override
    public void run(String... args) {
        // Create sample users
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john@example.com");
        user1.setFullName("John Doe");
        user1.setRole(UserRole.USER);
        userRepository.save(user1);
        
        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setEmail("jane@example.com");
        user2.setFullName("Jane Smith");
        user2.setRole(UserRole.USER);
        userRepository.save(user2);
        
        // Create sample admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@example.com");
        admin.setFullName("Admin User");
        admin.setRole(UserRole.ADMIN);
        userRepository.save(admin);
        
        System.out.println("==============================================");
        System.out.println("Sample Data Initialized:");
        System.out.println("Users: john_doe (ID: 1), jane_smith (ID: 2)");
        System.out.println("Admin: admin (ID: 3)");
        System.out.println("==============================================");
    }
}

