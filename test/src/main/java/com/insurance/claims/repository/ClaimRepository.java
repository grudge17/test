package com.insurance.claims.repository;

import com.insurance.claims.model.Claim;
import com.insurance.claims.model.ClaimStatus;
import com.insurance.claims.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Claim entity
 * Spring Data JPA will automatically implement this interface
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    
    List<Claim> findByUser(User user);
    
    List<Claim> findByStatus(ClaimStatus status);
    
    Optional<Claim> findByClaimNumber(String claimNumber);
    
    List<Claim> findByUserOrderByCreatedAtDesc(User user);
    
    List<Claim> findAllByOrderByCreatedAtDesc();
}

