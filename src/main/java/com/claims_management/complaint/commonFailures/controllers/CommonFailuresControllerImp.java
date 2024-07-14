package com.claims_management.complaint.commonFailures.controllers;

import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.complaint.commonFailures.Dto.CommonFailuresRequest;
import com.claims_management.complaint.commonFailures.Dto.CommonFailuresResponse;
import com.claims_management.complaint.commonFailures.Dto.UpdateCommonFailures;
import com.claims_management.complaint.commonFailures.service.CommonFailuresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.complaint.commonFailures.constants.commonFailuresConstants.COMMON_FAILURES;
import static com.claims_management.commons.constants.GlobalApiConstant.*;

@RestController
@RequestMapping(COMMON_FAILURES)
public class CommonFailuresControllerImp extends GenericRestController implements CommonFailuresController {


    private final CommonFailuresService commonFailuresService;

    public CommonFailuresControllerImp(CommonFailuresService commonFailuresService){
        this.commonFailuresService = commonFailuresService;
    }


    @Override
    public ResponseEntity<CustomResponse> createFailures(CommonFailuresRequest commonFailuresRequest) {
        return ok(commonFailuresService.save(commonFailuresRequest),CREATED,COMMON_FAILURES);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllFailures(int numberPage) {
        return ok(commonFailuresService.getAll(numberPage),null,COMMON_FAILURES);
    }

    @Override
    public ResponseEntity<CustomResponse> getFailuresById(Long id) {
        CommonFailuresResponse commonFailuresResponse = commonFailuresService.getById(id);
        return ok(commonFailuresResponse,null,COMMON_FAILURES);
    }

    @Override
    public ResponseEntity<CustomResponse> updateFailures(UpdateCommonFailures updateCommonFailures) {
        CommonFailuresResponse commonFailuresResponse = commonFailuresService.update(updateCommonFailures);
        return ok(commonFailuresResponse,null,COMMON_FAILURES);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteFailuresById(Long id) {
        commonFailuresService.delete(id);
        return ok(null,DELETED_SUCCESSFULLY,COMMON_FAILURES);
    }
}
