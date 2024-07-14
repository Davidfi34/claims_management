package com.claims_management.adm.Dto;

import com.claims_management.address.Dto.UpdateAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record UpdateAdm(
        @NotNull
        Long id,
        @NotEmpty
        String name,

        UpdateAddress address,
        @NotEmpty
        String phone,
        @Email
        String email
        ) {
}
