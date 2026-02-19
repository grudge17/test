package com.insurance.claims.dto;

import com.insurance.claims.model.ClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for claim response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimResponse {
    
    private Long id;
    private String claimNumber;
    private String description;
    private BigDecimal claimAmount;
    private ClaimStatus status;
    private String userName;
    private Long userId;
    private String reviewedByName;
    private String reviewComments;
    private LocalDateTime createdAt;
    private LocalDateTime reviewedAt;
}

