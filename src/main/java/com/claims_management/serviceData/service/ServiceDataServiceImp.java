package com.claims_management.serviceData.service;

import com.claims_management.infra.errors.IntegrityValidation;
import com.claims_management.serviceData.Dto.ServiceRequest;
import com.claims_management.serviceData.Dto.ServiceResponse;
import com.claims_management.serviceData.Dto.UpdateService;
import com.claims_management.serviceData.models.ServiceData;
import com.claims_management.serviceData.repository.ServiceDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceDataServiceImp implements ServiceDataService {

    private final ServiceDataRepository serviceDataRepository;

    public ServiceDataServiceImp(ServiceDataRepository serviceDataRepository){
        this.serviceDataRepository = serviceDataRepository;
    }

    @Override
    public ServiceResponse save(ServiceRequest serviceRequest) {
        ServiceData serviceData = new ServiceData(null,serviceRequest.name(),serviceRequest.description());
        return new ServiceResponse(serviceDataRepository.save(serviceData));
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        Optional<ServiceData> serviceDataOptional = serviceDataRepository.findById(id);
        if (serviceDataOptional.isPresent()) return new ServiceResponse(serviceDataOptional.get());
        throw new IntegrityValidation("Service not found");
    }

    @Override
    public Page<ServiceResponse> getAllService(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return serviceDataRepository.findAll(pageable).map(ServiceResponse::new);
    }

    @Override
    public ServiceResponse updateService(UpdateService updateService) {
        Optional<ServiceData> serviceDataOptional = serviceDataRepository.findById(updateService.id());
        if (serviceDataOptional.isPresent()) {
            ServiceData serviceData = new ServiceData(updateService.id(), updateService.name(), updateService.description());
            return new ServiceResponse(serviceDataRepository.save(serviceData));

        }
        throw new IntegrityValidation("Service not found");
    }

    @Override
    public void deleteService(Long id) {
        Optional<ServiceData> serviceDataOptional = serviceDataRepository.findById(id);
        if (!serviceDataOptional.isPresent()) throw new IntegrityValidation("Service not found");
        serviceDataRepository.deleteById(id);
    }

}