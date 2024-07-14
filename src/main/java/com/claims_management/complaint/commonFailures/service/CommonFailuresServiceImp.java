package com.claims_management.complaint.commonFailures.service;

import com.claims_management.complaint.commonFailures.Dto.CommonFailuresRequest;
import com.claims_management.complaint.commonFailures.Dto.CommonFailuresResponse;
import com.claims_management.complaint.commonFailures.Dto.UpdateCommonFailures;
import com.claims_management.complaint.commonFailures.Dto.mapper.CommonFailuresMapper;
import com.claims_management.complaint.commonFailures.models.CommonFailures;
import com.claims_management.complaint.commonFailures.repository.CommonFailuresRepository;
import com.claims_management.infra.errors.IntegrityValidation;
import com.claims_management.serviceData.Dto.mapper.ServiceDataMapper;
import com.claims_management.serviceData.models.ServiceData;
import com.claims_management.serviceData.service.ServiceDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonFailuresServiceImp implements CommonFailuresService {

    private final CommonFailuresRepository commonFailuresRepository;
    private final ServiceDataService serviceDataService;
    private final CommonFailuresMapper commonFailuresMapper;
    private final ServiceDataMapper serviceDataMapper;

    public CommonFailuresServiceImp(CommonFailuresRepository commonFailuresRepository,
                                    ServiceDataService serviceDataService,
                                    CommonFailuresMapper commonFailuresMapper,
                                    ServiceDataMapper serviceDataMapper){
        this.commonFailuresRepository = commonFailuresRepository;
        this.serviceDataService = serviceDataService;
        this.commonFailuresMapper = commonFailuresMapper;
        this.serviceDataMapper = serviceDataMapper;
    }


    @Override
    public CommonFailuresResponse save(CommonFailuresRequest commonFailuresRequest) {
        return new CommonFailuresResponse(commonFailuresRepository.save(
                commonFailuresMapper.commonFailuresRequestToCommonFailures(commonFailuresRequest)));
    }

    @Override
    public CommonFailuresResponse getById(Long id) {
        Optional<CommonFailures> commonFailuresOptional = commonFailuresRepository.findById(id);
        if (commonFailuresOptional.isPresent()) {
            return new CommonFailuresResponse(commonFailuresOptional.get());
        }
        throw new IntegrityValidation("commonFailures not found");
    }

    @Override
    public Page<CommonFailuresResponse> getAll(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return commonFailuresRepository.findAll(pageable)
                .map(commonFailures -> new CommonFailuresResponse(commonFailures));
    }

    @Override
    public CommonFailuresResponse update(UpdateCommonFailures updateCommonFailures) {

        Optional<CommonFailures> commonFailuresOptional = commonFailuresRepository.findById(updateCommonFailures.id());

        if (commonFailuresOptional.isPresent()) {
            ServiceData serviceData = serviceDataMapper
                    .serviceResponseToServiceData(serviceDataService.getServiceById(updateCommonFailures.id_service()));

            CommonFailures commonFailures = new CommonFailures();
            commonFailures.setId(updateCommonFailures.id());
            commonFailures.setName(updateCommonFailures.name());
            commonFailures.setDescription(updateCommonFailures.description());
            commonFailures.setServiceData(serviceData);

            return new CommonFailuresResponse(commonFailuresRepository.save(commonFailures));
        }
        throw new IntegrityValidation("commonFailures not found");
    }

    @Override
    public void delete(Long id) {

    }
















    /*private final CommonFailuresRepository consortiumRepository;
    private final CommonFailuresMapper consortiumMapper;
    private final AdmService admService;
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final AdmMapper admMapper;

    public ConsortiumServiceImp(CommonFailuresRepository consortiumRepository,
                                CommonFailuresMapper consortiumMapper,
                                AdmService admService,
                                AddressService addressService,
                                AddressMapper addressMapper,
                                AdmMapper admMapper
                                ){
        this.consortiumRepository = consortiumRepository;
        this.consortiumMapper = consortiumMapper;
        this.admService = admService;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.admMapper = admMapper;
    }

    @Override
    public CommonFailuresResponse save(CommonFailuresRequest consortiumRequest) {
        //TODO: get Adm by id
        AdmResponse admResponse = admService.getAdmById(consortiumRequest.id_adm());
        if (admResponse == null) {
            throw new IllegalArgumentException("adm not found");
        }
        //TODO: save Address
        AddressResponse addressResponse = addressService.save(consortiumRequest.address());

        //TODO: Create and save Consortium
        commonFailures consortium = new commonFailures(null,
                consortiumRequest.name(),
                addressMapper.addressResponseToAddress(addressResponse),
                admMapper.admResponseToAdm(admResponse));
        return new CommonFailuresResponse(consortiumRepository.save(consortium));
    }

    @Override
    public CommonFailuresResponse getConsortiumById(Long id) {
        Optional<commonFailures> consortiumOptional = consortiumRepository.findById(id);
        if (consortiumOptional.isPresent()) {
            return consortiumMapper.consortiumToConsortiumResponse(consortiumOptional.get());
        }
        throw new IntegrityValidation("consortium not found");
    }

    @Override
    public Page<CommonFailuresResponse> getAllConsortium(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return consortiumRepository.findAll(pageable)
                .map(consortium -> consortiumMapper.consortiumToConsortiumResponse(consortium));
    }

    @Override
    public CommonFailuresResponse updateConsortium(UpdateConsortium updateConsortium) {

        Optional<commonFailures> consortiumOptional = consortiumRepository.findById(updateConsortium.id());

        if (consortiumOptional.isPresent()) {
            AddressResponse addressResponse = addressService.getAddressById(updateConsortium.address().id());
            AdmResponse admResponse = admService.getAdmById(updateConsortium.id_adm());

            if (addressResponse == null || admResponse == null) {
                throw new IllegalArgumentException("address or adm not found");
            }
            //TODO: UPDATE ADDRESS
            Address address = addressMapper.addressResponseToAddress(
                        addressService.updateAddress(updateConsortium.address()));

            //TODO: CREATE CONSORTIUM
            commonFailures c = new commonFailures(
                        updateConsortium.id(),
                        updateConsortium.name(),
                        address,
                        admMapper.admResponseToAdm(admResponse)
            );
            return new CommonFailuresResponse(consortiumMapper.consortiumBuild(consortiumRepository.save(c)));
        }

            throw new IntegrityValidation("consortium not found");
    }

    @Override
    public void deleteConsortium(Long id) {
        Optional<commonFailures> consortiumOptional = consortiumRepository.findById(id);
        if (!consortiumOptional.isPresent()) throw new IntegrityValidation("Consortium not found");
        consortiumRepository.deleteById(id);
    }*/

}