package com.claims_management.complaint.controllers;

import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.complaint.Dto.ComplaintRequest;
import com.claims_management.complaint.Dto.UpdateComplaint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.*;


public interface ComplaintController {


    @PostMapping
    ResponseEntity<CustomResponse> createComplaint(@RequestBody ComplaintRequest complaintRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllComplaint(@PathVariable int numberPage);

    @GetMapping(ACTIVE_PAGER_REGISTRATION)
    ResponseEntity<CustomResponse> getAllActiveComplaint(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getComplaintById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateComplaint(@RequestBody UpdateComplaint updateComplaint);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteComplaintById(@PathVariable Long id);

}
