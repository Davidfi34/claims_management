package com.claims_management.consortium.controllers;

import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.consortium.Dto.ConsortiumRequest;
import com.claims_management.consortium.Dto.UpdateConsortium;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.claims_management.commons.constants.GlobalApiConstant.ID_PARAM;


public interface ConsortiumController {


    @PostMapping
    ResponseEntity<CustomResponse> createConsortium(@RequestBody ConsortiumRequest consortiumRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllConsortiums(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getConsortiumById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateConsortium(@RequestBody UpdateConsortium updateConsortium);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteConsortiumById(@PathVariable Long id);

}
