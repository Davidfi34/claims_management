package com.claims_management.subscriber.Dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record UpdateSubscriber(
        @NotNull
        Long id,
        @NotNull
        Long idConsortium,
        @NotNull
        Long idService,
        LocalDate startDate,
        LocalDate endDate ) {
}
