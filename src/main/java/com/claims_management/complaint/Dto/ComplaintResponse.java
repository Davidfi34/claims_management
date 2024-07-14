package com.claims_management.complaint.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import com.claims_management.complaint.models.Complaint;


public record ComplaintResponse(Long id, String description, String firstname, String lastname ,
                                LocalDateTime date, String phone , Long id_consortium , Long id_service, Long id_failures, Boolean active ) {
    public ComplaintResponse(Complaint c){
        this(c.getId(),c.getDescription(), c.getFirstname(), c.getLastname(),
                c.getDate(), c.getPhone(), c.getConsortium().getId(),
                c.getServiceData().getId(),
                (c.getCommonFailures() != null) ? c.getCommonFailures().getId() : null
                , c.isActive());
    }
}