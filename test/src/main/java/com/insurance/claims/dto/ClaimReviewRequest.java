package com.insurance.claims.dto;

import com.insurance.claims.model.ClaimStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for admin to review a claim
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimReviewRequest {
    
    @NotNull(message = "Status is required")
    private ClaimStatus status;
    
    private String reviewComments;
    
    @NotNull(message = "Admin ID is required")
    private Long adminId;
}

