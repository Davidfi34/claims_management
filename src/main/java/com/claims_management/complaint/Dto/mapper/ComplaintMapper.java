package com.claims_management.complaint.Dto.mapper;

import com.claims_management.complaint.Dto.ComplaintRequest;
import com.claims_management.complaint.Dto.ComplaintResponse;
import com.claims_management.complaint.models.Complaint;
import com.claims_management.serviceData.Dto.mapper.ServiceDataMapper;
import com.claims_management.serviceData.models.ServiceData;
import com.claims_management.serviceData.service.ServiceDataService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ComplaintMapper {



    private final ServiceDataService serviceDataService;
    private final ServiceDataMapper serviceDataMapper;

    public ComplaintMapper(ServiceDataService serviceDataService,
                           ServiceDataMapper serviceDataMapper){
        this.serviceDataService = serviceDataService;
        this.serviceDataMapper = serviceDataMapper;
    }

    public Complaint complaintRequestToComplaint(ComplaintRequest complaintRequest){
        Complaint complaint = new Complaint();
        //ServiceData serviceData =
        complaint.setDescription(complaintRequest.description());
        complaint.setFirstname(complaintRequest.firstname());
        complaint.setLastname(complaintRequest.lastname());
        complaint.setPhone(complaintRequest.phone());
        complaint.setDate(LocalDateTime.now());
       // complaint.setConsortium();
       // complaint.setServiceData();
        //complaint.setCommonFailures();

        return null;
    }




    /*public Complaint commonFailuresRequestToCommonFailures(ComplaintRequest commonFailuresRequest){
        Complaint commonFailures = new Complaint();
        ServiceData serviceData = serviceDataMapper.
                serviceResponseToServiceData(serviceDataService.getServiceById(commonFailuresRequest.id_service()));
        commonFailures.setName(commonFailuresRequest.name());
        commonFailures.setDescription(commonFailuresRequest.description());
        commonFailures.setServiceData(serviceData);
        return commonFailures;
    }


    public Complaint commonFailuresResponseToCommonFailures(ComplaintResponse commonFailuresResponse){
        Complaint commonFailures = new Complaint();

        ServiceData serviceData = serviceDataMapper.
                serviceResponseToServiceData(serviceDataService.getServiceById(commonFailuresResponse.id_service()));

        commonFailures.setId(commonFailuresResponse.id());
        commonFailures.setName(commonFailuresResponse.name());
        commonFailures.setDescription(commonFailuresResponse.description());
        commonFailures.setServiceData(serviceData);
        return commonFailures;
    }


    /*private final AddressMapper addressMapper;
    private final AdmMapper admMapper;

    public ConsortiumMapper( AddressMapper addressMapper,
                             AdmMapper admMapper){
        this.addressMapper = addressMapper;
        this.admMapper = admMapper;
    }

    public commonFailures consortiumResponseToConsortium(ConsortiumResponse consortiumResponse){
        commonFailures consortium = new commonFailures();

        consortium.setId(consortiumResponse.id());
        consortium.setName(consortiumResponse.name());
        consortium.setAddress(consortiumResponse.address());
        consortium.setAdm(consortiumResponse.adm());
        return consortium;
    }



    public ConsortiumResponse consortiumToConsortiumResponse(commonFailures consortium){
        consortium.setAddress(addressMapper.addressBuild(consortium.getAddress()));
        consortium.setAdm(admMapper.admBuild(consortium.getAdm()));
        return new ConsortiumResponse(consortium);
    }

    public commonFailures consortiumBuild (commonFailures consortium){
       commonFailures c = new commonFailures();
       c.setId(consortium.getId());
       c.setName(consortium.getName());
       c.setAddress(addressMapper.addressBuild(consortium.getAddress()));
       c.setAdm(admMapper.admBuild(consortium.getAdm()));
       return c;
    }*/


}
