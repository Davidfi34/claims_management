package com.claims_management.consortium.service;

import com.claims_management.consortium.Dto.ConsortiumRequest;
import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.Dto.UpdateConsortium;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface ConsortiumService {

    public ConsortiumResponse save(ConsortiumRequest consortiumRequest);
    public ConsortiumResponse getConsortiumById(Long id);
    public Page<ConsortiumResponse> getAllConsortium(int numberPage);
    public ConsortiumResponse updateConsortium(UpdateConsortium updateConsortium);
    public void deleteConsortium(Long id);
}
