package com.claims_management.serviceData.Dto;

import jakarta.validation.constraints.NotEmpty;

public record ServiceRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String description) {
}
