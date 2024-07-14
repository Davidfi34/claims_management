package com.claims_management.address.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record UpdateAddress(
        @NotNull
        Long id,
        @NotEmpty
        String street,
        @NotEmpty
        String number,
        @NotEmpty
        String apartment_number,
        @NotEmpty
        String city,
        @NotEmpty
        String province,
        @NotEmpty
        String postal_code
        ) {

}
