package com.claims_management.serviceData.controllers;

import com.claims_management.commons.dto.response.CustomResponse;

import com.claims_management.serviceData.Dto.ServiceRequest;
import com.claims_management.serviceData.Dto.UpdateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.claims_management.commons.constants.GlobalApiConstant.ID_PARAM;


public interface ServiceDataController {


    @PostMapping
    ResponseEntity<CustomResponse> createService(@Valid @RequestBody ServiceRequest serviceRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllServices(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getServiceById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateService(@Valid @RequestBody UpdateService updateService);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteServiceById(@PathVariable Long id);

}
