package com.claims_management.consortium.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ConsortiumRequest(
        @NotEmpty
        String street,
        @NotEmpty
        String number,
        String apartment_number,
        @NotEmpty
        String city,
        @NotEmpty
        String province,
        @NotEmpty
        String postal_code,
        @NotNull
        Long id_adm) {
}