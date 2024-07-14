package com.claims_management.consortium.service;

import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.mapper.AddressMapper;
import com.claims_management.address.models.Address;
import com.claims_management.address.service.AddressService;
import com.claims_management.adm.Dto.AdmResponse;
import com.claims_management.adm.Dto.mapper.AdmMapper;
import com.claims_management.adm.service.AdmService;
import com.claims_management.consortium.Dto.ConsortiumRequest;
import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.Dto.UpdateConsortium;
import com.claims_management.consortium.Dto.mapper.ConsortiumMapper;
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
    private final ConsortiumMapper consortiumMapper;
    private final AdmService admService;
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final AdmMapper admMapper;

    public ConsortiumServiceImp(ConsortiumRepository consortiumRepository,
                                ConsortiumMapper consortiumMapper,
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
    public ConsortiumResponse save(ConsortiumRequest consortiumRequest) {
        //TODO: get Adm by id
        AdmResponse admResponse = admService.getAdmById(consortiumRequest.id_adm());

        //TODO: save Address
        AddressResponse addressResponse = addressService.save(consortiumRequest.address());

        //TODO: Create and save Consortium
        Consortium consortium = new Consortium(null,
                consortiumRequest.name(),
                addressMapper.addressResponseToAddress(addressResponse),
                admMapper.admResponseToAdm(admResponse));
        return new ConsortiumResponse(consortiumRepository.save(consortium));
    }

    @Override
    public ConsortiumResponse getConsortiumById(Long id) {
        Optional<Consortium> consortiumOptional = consortiumRepository.findById(id);
        if (consortiumOptional.isPresent()) {
            return consortiumMapper.consortiumToConsortiumResponse(consortiumOptional.get());
        }
        throw new IntegrityValidation("consortium not found");
    }

    @Override
    public Page<ConsortiumResponse> getAllConsortium(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return consortiumRepository.findAll(pageable)
                .map(consortium -> consortiumMapper.consortiumToConsortiumResponse(consortium));
    }

    @Override
    public ConsortiumResponse updateConsortium(UpdateConsortium updateConsortium) {

        Optional<Consortium> consortiumOptional = consortiumRepository.findById(updateConsortium.id());

        if (consortiumOptional.isPresent()) {
            AdmResponse admResponse = admService.getAdmById(updateConsortium.id_adm());

            //TODO: UPDATE ADDRESS
            Address address = addressMapper.addressResponseToAddress(
                        addressService.updateAddress(updateConsortium.address()));

            //TODO: CREATE CONSORTIUM
            Consortium c = new Consortium(
                        updateConsortium.id(),
                        updateConsortium.name(),
                        address,
                        admMapper.admResponseToAdm(admResponse)
            );
            return new ConsortiumResponse(consortiumMapper.consortiumBuild(consortiumRepository.save(c)));
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