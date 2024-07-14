package com.claims_management.complaint.commonFailures.service;

import com.claims_management.complaint.commonFailures.Dto.CommonFailuresRequest;
import com.claims_management.complaint.commonFailures.Dto.CommonFailuresResponse;
import com.claims_management.complaint.commonFailures.Dto.UpdateCommonFailures;
import org.springframework.data.domain.Page;


public interface CommonFailuresService {

    public CommonFailuresResponse save(CommonFailuresRequest commonFailuresRequest);
    public CommonFailuresResponse getById(Long id);
    public Page<CommonFailuresResponse> getAll(int numberPage);
    public CommonFailuresResponse update(UpdateCommonFailures updateCommonFailures);
    public void delete(Long id);
}
