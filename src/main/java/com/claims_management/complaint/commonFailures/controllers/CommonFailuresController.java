package com.claims_management.complaint.commonFailures.controllers;

import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.complaint.commonFailures.Dto.CommonFailuresRequest;
import com.claims_management.complaint.commonFailures.Dto.UpdateCommonFailures;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.claims_management.commons.constants.GlobalApiConstant.ID_PARAM;


public interface CommonFailuresController {


    @PostMapping
    ResponseEntity<CustomResponse> createFailures(@RequestBody CommonFailuresRequest commonFailuresRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllFailures(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getFailuresById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateFailures(@RequestBody UpdateCommonFailures updateCommonFailures);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteFailuresById(@PathVariable Long id);

}
