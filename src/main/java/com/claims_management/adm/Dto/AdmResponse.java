package com.claims_management.adm.Dto;

import com.claims_management.address.models.Address;
import com.claims_management.adm.models.Adm;


public record AdmResponse(Long id, String name, Address address, String phone, String email ) {
    public AdmResponse(Adm adm){
        this(adm.getId(), adm.getName(), adm.getAddress(),adm.getPhone(), adm.getEmail());
    }
}

