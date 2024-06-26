package com.claims_management.adm.service;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.UpdateAddress;
import com.claims_management.address.models.Address;
import com.claims_management.address.service.AddressService;
import com.claims_management.adm.Dto.AdmRequest;
import com.claims_management.adm.Dto.AdmResponse;
import com.claims_management.adm.Dto.UpdateAdm;
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

    public AdmServiceImp(AdmRepository admRepository, AddressService addressService){
        this.admRepository = admRepository;
        this.addressService = addressService;
    }

    @Override
    public AdmResponse save(AdmRequest admRequest) {
        // save Address
        AddressResponse addressData = addressService.save(
                new AddressRequest(admRequest.street(), admRequest.number(),
                        admRequest.apartment_number(), admRequest.city(),
                        admRequest.province(), admRequest.postal_code()));

        Address address = new Address(addressData.id(), addressData.street(), addressData.number(),
                addressData.apartment_number(), addressData.city(),
                addressData.province(), addressData.postal_code());

        Adm adm = new Adm(null,admRequest.name(), address,admRequest.phone(), admRequest.email());
        return new AdmResponse(admRepository.save(adm));
    }

    @Override
    public AdmResponse getAdmById(Long id) {
        Optional<Adm> admOptional = admRepository.findById(id);
        if (admOptional.isPresent()) return new AdmResponse(admOptional.get());
        throw new IntegrityValidation("adm not found");
    }

    @Override
    public Page<AdmResponse> getAllAdm(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return admRepository.findAll(pageable).map(AdmResponse::new);
    }

    @Override
    public AdmResponse updateAdm(UpdateAdm updateAdm) {

        AddressResponse addressResponse = addressService.getAddressById(updateAdm.address().id());

        if (addressResponse == null ){
            throw new IllegalArgumentException("address not found");
        }

        AddressResponse addressData = addressService.updateAddress( new UpdateAddress(updateAdm.address().id(),updateAdm.address().street(),
                updateAdm.address().number(), updateAdm.address().apartment_number(),
                updateAdm.address().city(),updateAdm.address().province(), updateAdm.address().postal_code()) );

        Address address = new Address(addressData.id(),addressData.street(),addressData.number(),
                addressData.apartment_number(), addressData.city(), addressData.province(),addressData.postal_code());

        Optional<Adm> admOptional = admRepository.findById(updateAdm.id());
        if (admOptional.isPresent()){
            Adm adm = new Adm(updateAdm.id(), updateAdm.name(),address,updateAdm.phone(),updateAdm.email());
            return new AdmResponse(admRepository.save(adm));
        }
        throw new IntegrityValidation("adm not found");
    }

    @Override
    public void deleteAdm(Long id) {
        Optional<Adm> admOptional = admRepository.findById(id);
        if (!admOptional.isPresent()) throw new IntegrityValidation("adm not found");
        admRepository.deleteById(id);
    }

}