package com.claims_management.adm.Dto.mapper;

import com.claims_management.address.Dto.mapper.AddressMapper;
import com.claims_management.adm.Dto.AdmResponse;
import com.claims_management.adm.models.Adm;
import org.springframework.stereotype.Component;

@Component
public class AdmMapper {

    private final AddressMapper addressMapper;

    public AdmMapper(AddressMapper addressMapper){
        this.addressMapper = addressMapper;
    }

    public Adm admResponseToAdm(AdmResponse admResponse){
        Adm adm = new Adm();
        adm.setId(admResponse.id());
        adm.setName(admResponse.name());
        adm.setAddress(addressMapper.addressBuild(admResponse.address()));
        adm.setPhone(admResponse.phone());
        adm.setEmail(admResponse.email());
        return adm;
    }

    public AdmResponse admToAdmResponse(Adm adm){
        adm.setAddress(addressMapper.addressBuild(adm.getAddress()));
        return new AdmResponse(adm);
    }

    public Adm admBuild(Adm adm){
        Adm a = new Adm();
        a.setId(adm.getId());
        a.setName(adm.getName());
        a.setAddress(addressMapper.addressBuild(adm.getAddress()));
        a.setEmail(adm.getEmail());
        a.setPhone(adm.getPhone());
        return a;
    }

}