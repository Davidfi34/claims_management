package com.claims_management.complaint.controllers;

import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.complaint.Dto.ComplaintRequest;
import com.claims_management.complaint.Dto.ComplaintResponse;
import com.claims_management.complaint.Dto.UpdateComplaint;
import com.claims_management.complaint.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.commons.constants.GlobalApiConstant.CREATED;
import static com.claims_management.commons.constants.GlobalApiConstant.DELETED_SUCCESSFULLY;
import static com.claims_management.complaint.constants.complaintConstants.COMPLAINT;

@RestController
@RequestMapping(COMPLAINT)
public class ComplaintControllerImp extends GenericRestController implements ComplaintController {


    private final ComplaintService complaintService;

    public ComplaintControllerImp(ComplaintService complaintService){
        this.complaintService = complaintService;
    }


    @Override
    public ResponseEntity<CustomResponse> createComplaint(ComplaintRequest complaintRequest) {
        return ok(complaintService.save(complaintRequest),CREATED,COMPLAINT);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllComplaint(int numberPage) {
        return ok(complaintService.getAll(numberPage),null,COMPLAINT);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllActiveComplaint(int numberPage) {
        return ok(complaintService.getAllActive(numberPage),null,COMPLAINT);
    }

    @Override
    public ResponseEntity<CustomResponse> getComplaintById(Long id) {
        ComplaintResponse complaintResponse = complaintService.getById(id);
        return ok(complaintResponse,null,COMPLAINT);
    }

    @Override
    public ResponseEntity<CustomResponse> updateComplaint(UpdateComplaint updateComplaint) {
        ComplaintResponse complaintResponse = complaintService.update(updateComplaint);
        return ok(complaintResponse,null,COMPLAINT);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteComplaintById(Long id) {
        complaintService.delete(id);
        return ok(null,DELETED_SUCCESSFULLY,COMPLAINT);
    }
}
