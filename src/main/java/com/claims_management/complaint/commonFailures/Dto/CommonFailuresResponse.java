package com.claims_management.complaint.commonFailures.Dto;

import com.claims_management.complaint.commonFailures.models.CommonFailures;


public record CommonFailuresResponse(Long id, String name, String description, Long id_service ) {
    public CommonFailuresResponse(CommonFailures c){
        this(c.getId(), c.getName(),c.getDescription(),c.getServiceData().getId());
    }
}

