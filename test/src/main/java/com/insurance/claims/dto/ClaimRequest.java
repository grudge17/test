package com.insurance.claims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for creating a new claim request
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimRequest {
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Claim amount is required")
    @Positive(message = "Claim amount must be positive")
    private BigDecimal claimAmount;
    
    @NotNull(message = "User ID is required")
    private Long userId;
}

