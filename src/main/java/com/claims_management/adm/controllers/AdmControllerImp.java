package com.claims_management.adm.controllers;

import com.claims_management.adm.Dto.AdmRequest;
import com.claims_management.adm.Dto.AdmResponse;
import com.claims_management.adm.Dto.UpdateAdm;
import com.claims_management.adm.service.AdmServiceImp;
import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.commons.constants.GlobalApiConstant.CREATED;
import static com.claims_management.commons.constants.GlobalApiConstant.DELETED_SUCCESSFULLY;
import static com.claims_management.adm.constants.AdmConstants.REQUEST_ADM;

@RestController
@RequestMapping(REQUEST_ADM)
public class AdmControllerImp extends GenericRestController implements AdmController {

    private final AdmServiceImp admServiceImp;

    public AdmControllerImp(AdmServiceImp admServiceImp){
        this.admServiceImp = admServiceImp;
    }


    @Override
    public ResponseEntity<CustomResponse> createAdm(AdmRequest admRequest) {
        return ok(admServiceImp.save(admRequest),CREATED,REQUEST_ADM);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllAdm(int numberPage) {
        return ok(admServiceImp.getAllAdm(numberPage),null, REQUEST_ADM);
    }

    @Override
    public ResponseEntity<CustomResponse> getAdmById(Long id) {
        AdmResponse admResponse = admServiceImp.getAdmById(id);
        return ok(admResponse,null,REQUEST_ADM);
    }

    @Override
    public ResponseEntity<CustomResponse> updateAdm(UpdateAdm updateAdm) {
        AdmResponse admResponse = admServiceImp.updateAdm(updateAdm);
        return ok(admResponse,null,REQUEST_ADM);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteAdmById(Long id) {
        admServiceImp.deleteAdm(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_ADM);
    }
}
