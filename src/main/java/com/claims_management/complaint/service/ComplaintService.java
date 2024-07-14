package com.claims_management.complaint.service;

import com.claims_management.complaint.Dto.ComplaintRequest;
import com.claims_management.complaint.Dto.ComplaintResponse;
import com.claims_management.complaint.Dto.UpdateComplaint;
import org.springframework.data.domain.Page;


public interface ComplaintService {

    public ComplaintResponse save(ComplaintRequest complaintRequest);
    public ComplaintResponse getById(Long id);
    public Page<ComplaintResponse> getAll(int numberPage);
    public Page<ComplaintResponse> getAllActive(int numberPage);
    public ComplaintResponse update(UpdateComplaint updateComplaint);
    public void delete(Long id);
}
