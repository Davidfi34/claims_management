package com.claims_management.serviceData.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record UpdateService(
        @NotNull
        Long id,
        @NotEmpty
        String name,
        @NotEmpty
        String description
        ) {
}
