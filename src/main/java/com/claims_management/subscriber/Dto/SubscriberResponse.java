package com.claims_management.subscriber.Dto;

import com.claims_management.subscriber.models.Subscriber;

import java.time.LocalDate;
import java.util.Date;

public record SubscriberResponse(Long id, Long idConsortium, Long idService, LocalDate startDate, LocalDate endDate ) {
    public SubscriberResponse(Subscriber s){
        this(s.getId(),s.getConsortium().getId(),s.getService().getId(),s.getStartDate(),s.getEndDate());
    }
}

