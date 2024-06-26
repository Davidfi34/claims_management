package com.claims_management.address.controllers;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.UpdateAddress;
import com.claims_management.address.service.AddressServiceImp;
import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.address.constants.AddressConstants.REQUEST_ADDRESS;
import static com.claims_management.commons.constants.GlobalApiConstant.CREATED;
import static com.claims_management.commons.constants.GlobalApiConstant.DELETED_SUCCESSFULLY;

@RestController
@RequestMapping(REQUEST_ADDRESS)
public class AddressControllerImp extends GenericRestController implements AddressController {

    private final AddressServiceImp addressServiceImp;

    public AddressControllerImp(AddressServiceImp addressServiceImp){
        this.addressServiceImp = addressServiceImp;
    }


    @Override
    public ResponseEntity<CustomResponse> createAddress(AddressRequest addressRequest) {
        return ok(addressServiceImp.save(addressRequest),CREATED,REQUEST_ADDRESS);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllAddress(int numberPage) {
        return ok(addressServiceImp.getAllAddress(numberPage),null, REQUEST_ADDRESS);
    }

    @Override
    public ResponseEntity<CustomResponse> getAddressById(Long id) {
        AddressResponse addressResponse = addressServiceImp.getAddressById(id);
        return ok(addressResponse,null,REQUEST_ADDRESS);
    }

    @Override
    public ResponseEntity<CustomResponse> updateAddress(UpdateAddress updateAddress) {
        AddressResponse addressResponse = addressServiceImp.updateAddress(updateAddress);
        return ok(addressResponse,null,REQUEST_ADDRESS);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteAddressById(Long id) {
        addressServiceImp.deleteAddress(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_ADDRESS);
    }
}
