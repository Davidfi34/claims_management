package com.claims_management.adm.service;

import com.claims_management.adm.Dto.AdmRequest;
import com.claims_management.adm.Dto.AdmResponse;
import com.claims_management.adm.Dto.UpdateAdm;
import org.springframework.data.domain.Page;


public interface AdmService {

    public AdmResponse save(AdmRequest admRequest);
    public AdmResponse getAdmById(Long id);
    public Page<AdmResponse> getAllAdm(int numberPage);
    public AdmResponse updateAdm(UpdateAdm adm);
    public void deleteAdm(Long id);
}
