package com.insurance.claims.model;

/**
 * Enum representing the status of a claim
 */
public enum ClaimStatus {
    PENDING,      // Initial status when claim is submitted
    IN_PROGRESS,  // Admin is reviewing the claim
    APPROVED,     // Claim has been approved
    REJECTED      // Claim has been rejected
}

