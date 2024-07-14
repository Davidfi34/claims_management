package com.claims_management.adm.service;

import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.mapper.AddressMapper;
import com.claims_management.address.service.AddressService;
import com.claims_management.adm.Dto.AdmRequest;
import com.claims_management.adm.Dto.AdmResponse;
import com.claims_management.adm.Dto.UpdateAdm;
import com.claims_management.adm.Dto.mapper.AdmMapper;
import com.claims_management.adm.models.Adm;
import com.claims_management.adm.repository.AdmRepository;
import com.claims_management.infra.errors.IntegrityValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmServiceImp implements AdmService {

    private final AdmRepository admRepository;
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final AdmMapper admMapper;

    public AdmServiceImp(AdmRepository admRepository,
                         AddressService addressService,
                         AddressMapper addressMapper,
                         AdmMapper admMapper){
        this.admRepository = admRepository;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.admMapper = admMapper;

    }

    //TODO: SAVE ADM
    @Override
    public AdmResponse save(AdmRequest admRequest) {
        //TODO: save Address
        AddressResponse addressResponse = addressService.save(admRequest.address());
        //TODO: create and save Adm
        Adm adm = new Adm(null,admRequest.name(),
                addressMapper.addressResponseToAddress(addressResponse),
                admRequest.phone(), admRequest.email());
        return new AdmResponse(admRepository.save(adm));
    }

    //TODO: GET ADM BY ID
    @Override
    public AdmResponse getAdmById(Long id) {
        Optional<Adm> admOptional = admRepository.findById(id);
        if (admOptional.isPresent()) return admMapper.admToAdmResponse(admOptional.get());
        throw new IntegrityValidation("adm not found");
    }

    //TODO: GET PAGE ADM
    @Override
    public Page<AdmResponse> getAllAdm(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return admRepository.findAll(pageable).map(adm -> admMapper.admToAdmResponse(adm));
    }

    //TODO: UPDATE ADM
    @Override
    public AdmResponse updateAdm(UpdateAdm updateAdm) {

        //TODO: update address
        AddressResponse address = addressService.updateAddress(updateAdm.address());

        Optional<Adm> admOptional = admRepository.findById(updateAdm.id());
        if (admOptional.isPresent()){
            Adm adm = new Adm(updateAdm.id(), updateAdm.name(),
                    addressMapper.addressResponseToAddress(address),
                    updateAdm.phone(),updateAdm.email());
            return new AdmResponse(admRepository.save(adm));
        }
        throw new IntegrityValidation("adm not found");
    }

    //TODO: DELETE ADM
    @Override
    public void deleteAdm(Long id) {
        Optional<Adm> admOptional = admRepository.findById(id);
        if (!admOptional.isPresent()) throw new IntegrityValidation("adm not found");
        admRepository.deleteById(id);
    }

}