package com.claims_management.adm.controllers;

import com.claims_management.adm.Dto.AdmRequest;
import com.claims_management.adm.Dto.UpdateAdm;
import com.claims_management.commons.dto.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.claims_management.commons.constants.GlobalApiConstant.ID_PARAM;


public interface AdmController {


    @PostMapping
    ResponseEntity<CustomResponse> createAdm(@RequestBody AdmRequest admRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllAdm(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getAdmById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateAdm(@RequestBody UpdateAdm updateAdm);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteAdmById(@PathVariable Long id);

}
