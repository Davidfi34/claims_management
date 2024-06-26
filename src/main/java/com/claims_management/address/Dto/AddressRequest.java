package com.claims_management.address.Dto;

import jakarta.validation.constraints.NotEmpty;

public record AddressRequest(
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
        String postal_code) {
}