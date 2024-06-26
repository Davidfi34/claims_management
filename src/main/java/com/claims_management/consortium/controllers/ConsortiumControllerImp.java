package com.claims_management.consortium.controllers;

import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.consortium.Dto.ConsortiumRequest;
import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.Dto.UpdateConsortium;
import com.claims_management.consortium.service.ConsortiumServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.commons.constants.GlobalApiConstant.*;
import static com.claims_management.consortium.constants.ConsortiumConstants.REQUEST_CONSORTIUM;

@RestController
@RequestMapping(REQUEST_CONSORTIUM)
public class ConsortiumControllerImp extends GenericRestController implements ConsortiumController {

    private final ConsortiumServiceImp consortiumServiceImp;

    public ConsortiumControllerImp(ConsortiumServiceImp consortiumServiceImp){
        this.consortiumServiceImp = consortiumServiceImp;
    }


    @Override
    public ResponseEntity<CustomResponse> createConsortium(ConsortiumRequest consortiumRequest) {
        return ok(consortiumServiceImp.save(consortiumRequest),CREATED,REQUEST_CONSORTIUM);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllConsortiums(int numberPage) {
        return ok(consortiumServiceImp.getAllConsortium(numberPage),null, REQUEST_CONSORTIUM);
    }

    @Override
    public ResponseEntity<CustomResponse> getConsortiumById(Long id) {
        ConsortiumResponse consortiumResponse = consortiumServiceImp.getConsortiumById(id);
        return ok(consortiumResponse,null,REQUEST_CONSORTIUM);
    }

    @Override
    public ResponseEntity<CustomResponse> updateConsortium(UpdateConsortium updateConsortium) {
        ConsortiumResponse consortiumResponse = consortiumServiceImp.updateConsortium(updateConsortium);
        return ok(consortiumResponse,null,REQUEST_CONSORTIUM);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteConsortiumById(Long id) {
        consortiumServiceImp.deleteConsortium(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_CONSORTIUM);
    }
}
