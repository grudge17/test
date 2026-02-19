package com.insurance.claims.controller;

import com.insurance.claims.dto.ClaimRequest;
import com.insurance.claims.dto.ClaimResponse;
import com.insurance.claims.dto.ClaimReviewRequest;
import com.insurance.claims.model.ClaimStatus;
import com.insurance.claims.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Claim operations
 * Handles HTTP requests related to claims
 */
@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClaimController {
    
    private final ClaimService claimService;
    
    /**
     * User submits a new claim
     */
    @PostMapping
    public ResponseEntity<ClaimResponse> createClaim(@Valid @RequestBody ClaimRequest request) {
        ClaimResponse claim = claimService.createClaim(request);
        return new ResponseEntity<>(claim, HttpStatus.CREATED);
    }
    
    /**
     * Admin reviews and updates claim status
     */
    @PutMapping("/{claimId}/review")
    public ResponseEntity<ClaimResponse> reviewClaim(
            @PathVariable Long claimId,
            @Valid @RequestBody ClaimReviewRequest request) {
        ClaimResponse claim = claimService.reviewClaim(claimId, request);
        return ResponseEntity.ok(claim);
    }
    
    /**
     * Get claim by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClaimResponse> getClaimById(@PathVariable Long id) {
        ClaimResponse claim = claimService.getClaimById(id);
        return ResponseEntity.ok(claim);
    }
    
    /**
     * Get all claims (for admin)
     */
    @GetMapping
    public ResponseEntity<List<ClaimResponse>> getAllClaims() {
        List<ClaimResponse> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }
    
    /**
     * Get claims by user ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClaimResponse>> getClaimsByUser(@PathVariable Long userId) {
        List<ClaimResponse> claims = claimService.getClaimsByUser(userId);
        return ResponseEntity.ok(claims);
    }
    
    /**
     * Get claims by status (for admin)
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ClaimResponse>> getClaimsByStatus(@PathVariable ClaimStatus status) {
        List<ClaimResponse> claims = claimService.getClaimsByStatus(status);
        return ResponseEntity.ok(claims);
    }
}

