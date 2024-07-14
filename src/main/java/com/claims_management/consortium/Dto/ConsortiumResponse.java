package com.claims_management.consortium.Dto;

import com.claims_management.address.models.Address;
import com.claims_management.adm.models.Adm;
import com.claims_management.consortium.models.Consortium;


public record ConsortiumResponse(Long id,String name, Address address, Adm adm ) {
    public ConsortiumResponse(Consortium c){
        this(c.getId(), c.getName(),c.getAddress(),c.getAdm());
    }
}

