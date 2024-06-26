package com.claims_management.serviceData.service;

import org.springframework.data.domain.Page;
import com.claims_management.serviceData.Dto.ServiceResponse;
import com.claims_management.serviceData.Dto.ServiceRequest;
import com.claims_management.serviceData.Dto.UpdateService;


public interface ServiceDataService {

    public ServiceResponse save(ServiceRequest serviceRequest);
    public ServiceResponse getServiceById(Long id);
    public Page<ServiceResponse> getAllService(int numberPage);
    public ServiceResponse updateService(UpdateService updateService);
    public void deleteService(Long id);
}
