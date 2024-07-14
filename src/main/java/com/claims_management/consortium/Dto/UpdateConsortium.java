package com.claims_management.consortium.Dto;

import com.claims_management.address.Dto.UpdateAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record UpdateConsortium(
        @NotNull
        Long id,
        String name,
        @NotNull
        UpdateAddress address,
        @NotNull
        Long id_adm
        ) {
}
