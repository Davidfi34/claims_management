package com.claims_management.complaint.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record UpdateComplaint(

        @NotNull
        Long id,
        @NotEmpty
        String description,
        @NotEmpty
        String firstname,
        @NotEmpty
        String lastname,
        @NotEmpty
        String phone,
        @NotNull
        Long id_service,
        @NotNull
        Long id_consortium,
        Long id_failures
        ) {
}
