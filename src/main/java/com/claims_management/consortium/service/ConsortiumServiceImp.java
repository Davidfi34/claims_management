package com.claims_management.consortium.service;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.UpdateAddress;
import com.claims_management.address.models.Address;
import com.claims_management.address.service.AddressService;
import com.claims_management.adm.Dto.AdmResponse;
import com.claims_management.adm.models.Adm;
import com.claims_management.adm.service.AdmService;
import com.claims_management.consortium.Dto.ConsortiumRequest;
import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.Dto.UpdateConsortium;
import com.claims_management.consortium.models.Consortium;
import com.claims_management.consortium.repository.ConsortiumRepository;
import com.claims_management.infra.errors.IntegrityValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsortiumServiceImp implements ConsortiumService {

    private final ConsortiumRepository consortiumRepository;
    private final AdmService admService;
    private final AddressService addressService;

    public ConsortiumServiceImp(ConsortiumRepository consortiumRepository,
                                AdmService admService,
                                AddressService addressService){
        this.consortiumRepository = consortiumRepository;
        this.admService = admService;
        this.addressService = addressService;
    }

    @Override
    public ConsortiumResponse save(ConsortiumRequest consortiumRequest) {

        AdmResponse admResponse = admService.getAdmById(consortiumRequest.id_adm());
        if (admResponse == null) {
            throw new IllegalArgumentException("adm not found");
        }
        // save Address
        AddressResponse addressData = addressService.save(
                new AddressRequest(consortiumRequest.street(), consortiumRequest.number(),
                        consortiumRequest.apartment_number(), consortiumRequest.city(),
                        consortiumRequest.province(), consortiumRequest.postal_code()));

        // Create Adm y Address
        Adm adm = new Adm(admResponse.id(), admResponse.name(), admResponse.address(), admResponse.phone(),admResponse.email());
        Address address = new Address(addressData.id(), addressData.street(), addressData.number(),
                addressData.apartment_number(), addressData.city(), addressData.province(), addressData.postal_code());

        // Create and save Consortium
        Consortium consortium = new Consortium(null, address, adm);
        return new ConsortiumResponse(consortiumRepository.save(consortium));
    }

    @Override
    public ConsortiumResponse getConsortiumById(Long id) {
        Optional<Consortium> consortiumOptional = consortiumRepository.findById(id);
        if (consortiumOptional.isPresent()) return new ConsortiumResponse(consortiumOptional.get());
        throw new IntegrityValidation("consortium not found");
    }

    @Override
    public Page<ConsortiumResponse> getAllConsortium(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return consortiumRepository.findAll(pageable).map(ConsortiumResponse::new);
    }

    @Override
    public ConsortiumResponse updateConsortium(UpdateConsortium updateConsortium) {
        Optional<Consortium> consortiumOptional = consortiumRepository.findById(updateConsortium.id());
        AddressResponse addressResponse = addressService.getAddressById(updateConsortium.address().getId());
        AdmResponse admResponse = admService.getAdmById(updateConsortium.adm().getId());

        if (addressResponse == null || admResponse == null){
            throw new IllegalArgumentException("address or adm not found");
        }
        addressService.updateAddress(new UpdateAddress(addressResponse.id(),addressResponse.street(),
                addressResponse.number(),addressResponse.apartment_number(),addressResponse.city(),
                addressResponse.province(),addressResponse.postal_code()));

        //TODO: SAVE ADDRESS
        Address address = new Address(updateConsortium.address().getId(),updateConsortium.address().getStreet(),
                updateConsortium.address().getNumber(),updateConsortium.address().getApartment_number(),
                updateConsortium.address().getCity(),updateConsortium.address().getProvince(),
                updateConsortium.address().getPostal_code());

        //TODO: UPDATE ADM

        if (consortiumOptional.isPresent()){
            Consortium consortium = new Consortium(updateConsortium.id(),address,updateConsortium.adm());
            return new ConsortiumResponse(consortiumRepository.save(consortium));
        }
        throw new IntegrityValidation("consortium not found");
    }

    @Override
    public void deleteConsortium(Long id) {
        Optional<Consortium> consortiumOptional = consortiumRepository.findById(id);
        if (!consortiumOptional.isPresent()) throw new IntegrityValidation("Consortium not found");
        consortiumRepository.deleteById(id);
    }

}