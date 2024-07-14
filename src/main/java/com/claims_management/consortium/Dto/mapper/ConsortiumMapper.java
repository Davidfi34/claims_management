package com.claims_management.consortium.Dto.mapper;

import com.claims_management.address.Dto.mapper.AddressMapper;
import com.claims_management.adm.Dto.mapper.AdmMapper;
import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.models.Consortium;
import org.springframework.stereotype.Component;

@Component
public class ConsortiumMapper {

    private final AddressMapper addressMapper;
    private final AdmMapper admMapper;

    public ConsortiumMapper( AddressMapper addressMapper,
                             AdmMapper admMapper){
        this.addressMapper = addressMapper;
        this.admMapper = admMapper;
    }

    public Consortium consortiumResponseToConsortium(ConsortiumResponse consortiumResponse){
        Consortium consortium = new Consortium();

        consortium.setId(consortiumResponse.id());
        consortium.setName(consortiumResponse.name());
        consortium.setAddress(consortiumResponse.address());
        consortium.setAdm(consortiumResponse.adm());
        return consortium;
    }



    public ConsortiumResponse consortiumToConsortiumResponse(Consortium consortium){
        consortium.setAddress(addressMapper.addressBuild(consortium.getAddress()));
        consortium.setAdm(admMapper.admBuild(consortium.getAdm()));
        return new ConsortiumResponse(consortium);
    }

    public Consortium consortiumBuild (Consortium consortium){
       Consortium c = new Consortium();
       c.setId(consortium.getId());
       c.setName(consortium.getName());
       c.setAddress(addressMapper.addressBuild(consortium.getAddress()));
       c.setAdm(admMapper.admBuild(consortium.getAdm()));
       return c;
    }


}
