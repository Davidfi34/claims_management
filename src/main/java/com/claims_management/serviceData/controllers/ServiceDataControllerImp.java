package com.claims_management.serviceData.controllers;

import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.serviceData.Dto.ServiceRequest;
import com.claims_management.serviceData.Dto.ServiceResponse;
import com.claims_management.serviceData.Dto.UpdateService;

import com.claims_management.serviceData.service.ServiceDataServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.commons.constants.GlobalApiConstant.*;
import static com.claims_management.serviceData.constants.ServiceDataConstants.REQUEST_SERVICE;

@RestController
@RequestMapping(REQUEST_SERVICE)
public class ServiceDataControllerImp extends GenericRestController implements ServiceDataController {

    private final ServiceDataServiceImp serviceImp;

    public ServiceDataControllerImp(ServiceDataServiceImp serviceImp){
        this.serviceImp = serviceImp;
    }


    @Override
    public ResponseEntity<CustomResponse> createService(ServiceRequest serviceRequest) {
        return ok(serviceImp.save(serviceRequest),CREATED,REQUEST_SERVICE);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllServices(int numberPage) {
        return ok(serviceImp.getAllService(numberPage),null, REQUEST_SERVICE);
    }

    @Override
    public ResponseEntity<CustomResponse> getServiceById(Long id) {
        ServiceResponse serviceResponse = serviceImp.getServiceById(id);
        return ok(serviceResponse,null,REQUEST_SERVICE);
    }

    @Override
    public ResponseEntity<CustomResponse> updateService(UpdateService updateService) {
        ServiceResponse serviceResponse = serviceImp.updateService(updateService);
        return ok(serviceResponse,null,REQUEST_SERVICE);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteServiceById(Long id) {
        serviceImp.deleteService(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_SERVICE);
    }
}
