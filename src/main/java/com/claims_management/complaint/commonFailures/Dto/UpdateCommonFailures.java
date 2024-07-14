package com.claims_management.complaint.commonFailures.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record UpdateCommonFailures(
        @NotNull
        Long id,
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        @NotNull
        Long id_service

        ) {
}
