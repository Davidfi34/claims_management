package com.claims_management.consortium.Dto;

import com.claims_management.address.Dto.AddressRequest;
import jakarta.validation.constraints.NotNull;

public record ConsortiumRequest(
        String name,
        @NotNull
        AddressRequest address,
        @NotNull
        Long id_adm) {
}