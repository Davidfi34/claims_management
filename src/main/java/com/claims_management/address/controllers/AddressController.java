package com.claims_management.address.controllers;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.UpdateAddress;
import com.claims_management.commons.dto.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.claims_management.commons.constants.GlobalApiConstant.ID_PARAM;


public interface AddressController {


    @PostMapping
    ResponseEntity<CustomResponse> createAddress(@RequestBody AddressRequest addressRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllAddress(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getAddressById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateAddress(@RequestBody UpdateAddress updateAddress);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteAddressById(@PathVariable Long id);

}
