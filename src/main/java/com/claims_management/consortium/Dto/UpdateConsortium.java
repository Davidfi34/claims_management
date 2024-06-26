package com.claims_management.consortium.Dto;

import com.claims_management.address.models.Address;
import com.claims_management.adm.models.Adm;
import jakarta.validation.constraints.NotNull;


public record UpdateConsortium(
        @NotNull
        Long id,
        @NotNull
        Address address,
        @NotNull
        Adm adm
        ) {
}
