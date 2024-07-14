package com.claims_management.adm.Dto;

import com.claims_management.address.Dto.AddressRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public record AdmRequest(
        @NotEmpty
        String name,
        AddressRequest address,
        @NotEmpty
        String phone,
        @Email
        String email
) {
}
