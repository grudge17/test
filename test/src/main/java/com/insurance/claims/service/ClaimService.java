package com.insurance.claims.service;

import com.insurance.claims.dto.ClaimRequest;
import com.insurance.claims.dto.ClaimResponse;
import com.insurance.claims.dto.ClaimReviewRequest;
import com.insurance.claims.model.Claim;
import com.insurance.claims.model.ClaimStatus;
import com.insurance.claims.model.User;
import com.insurance.claims.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service layer for Claim operations
 * Contains business logic for claim processing
 */
@Service
@RequiredArgsConstructor
public class ClaimService {
    
    private final ClaimRepository claimRepository;
    private final UserService userService;
    private final NotificationService notificationService;
    
    @Transactional
    public ClaimResponse createClaim(ClaimRequest request) {
        User user = userService.getUserById(request.getUserId());
        
        Claim claim = new Claim();
        claim.setClaimNumber(generateClaimNumber());
        claim.setDescription(request.getDescription());
        claim.setClaimAmount(request.getClaimAmount());
        claim.setStatus(ClaimStatus.PENDING);
        claim.setUser(user);
        
        Claim savedClaim = claimRepository.save(claim);
        
        // Notify user that claim was created
        notificationService.notifyUser(user.getId(), "Claim " + savedClaim.getClaimNumber() + " created successfully");
        
        return convertToResponse(savedClaim);
    }
    
    @Transactional
    public ClaimResponse reviewClaim(Long claimId, ClaimReviewRequest request) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + claimId));
        
        User admin = userService.getUserById(request.getAdminId());
        
        claim.setStatus(request.getStatus());
        claim.setReviewComments(request.getReviewComments());
        claim.setReviewedBy(admin);
        claim.setReviewedAt(LocalDateTime.now());
        
        Claim updatedClaim = claimRepository.save(claim);
        
        // Notify user about status change
        String statusMessage = String.format("Claim %s status updated to: %s", 
                claim.getClaimNumber(), request.getStatus());
        notificationService.notifyUser(claim.getUser().getId(), statusMessage);
        
        return convertToResponse(updatedClaim);
    }
    
    public ClaimResponse getClaimById(Long id) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + id));
        return convertToResponse(claim);
    }
    
    public List<ClaimResponse> getAllClaims() {
        return claimRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<ClaimResponse> getClaimsByUser(Long userId) {
        User user = userService.getUserById(userId);
        return claimRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<ClaimResponse> getClaimsByStatus(ClaimStatus status) {
        return claimRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    private String generateClaimNumber() {
        return "CLM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private ClaimResponse convertToResponse(Claim claim) {
        return ClaimResponse.builder()
                .id(claim.getId())
                .claimNumber(claim.getClaimNumber())
                .description(claim.getDescription())
                .claimAmount(claim.getClaimAmount())
                .status(claim.getStatus())
                .userName(claim.getUser().getFullName())
                .userId(claim.getUser().getId())
                .reviewedByName(claim.getReviewedBy() != null ? claim.getReviewedBy().getFullName() : null)
                .reviewComments(claim.getReviewComments())
                .createdAt(claim.getCreatedAt())
                .reviewedAt(claim.getReviewedAt())
                .build();
    }
}

