package com.claims_management.adm.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AdmRequest(
        @NotEmpty
        String name,
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
        @NotEmpty
        String phone,
        @Email
        String email
) {
}
