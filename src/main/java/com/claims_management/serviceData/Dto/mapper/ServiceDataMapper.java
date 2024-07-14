package com.claims_management.serviceData.Dto.mapper;

import com.claims_management.serviceData.Dto.ServiceResponse;
import com.claims_management.serviceData.models.ServiceData;
import org.springframework.stereotype.Component;

@Component
public class ServiceDataMapper {
    public ServiceData serviceResponseToServiceData(ServiceResponse serviceResponse){
        ServiceData serviceData = new ServiceData();
        serviceData.setId(serviceResponse.id());
        serviceData.setName(serviceResponse.name());
        serviceData.setDescription(serviceResponse.description());
        return serviceData;
    }
}
