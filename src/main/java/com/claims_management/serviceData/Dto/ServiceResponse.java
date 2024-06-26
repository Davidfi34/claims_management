package com.claims_management.serviceData.Dto;

import com.claims_management.serviceData.models.ServiceData;


public record ServiceResponse(Long id, String name, String description ) {
    public ServiceResponse(ServiceData s){
        this(s.getId(),s.getName(),s.getDescription());
    }
}

