package com.claims_management.subscriber.Dto;

import jakarta.validation.constraints.NotNull;

public record SubscriberRequest(
        @NotNull
        Long idConsortium,
        @NotNull
        Long idService ) {
}
