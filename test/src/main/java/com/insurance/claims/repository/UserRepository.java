package com.insurance.claims.repository;

import com.insurance.claims.model.User;
import com.insurance.claims.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity
 * Spring Data JPA will automatically implement this interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    List<User> findByRole(UserRole role);
    
    boolean existsByUsername(String username);
}

