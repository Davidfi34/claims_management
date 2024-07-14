package com.claims_management.address.service;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.UpdateAddress;
import com.claims_management.address.Dto.mapper.AddressMapper;
import com.claims_management.address.models.Address;
import com.claims_management.address.repository.AddressRepository;
import com.claims_management.infra.errors.IntegrityValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImp(AddressRepository addressRepository,AddressMapper addressMapper){
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressResponse save(AddressRequest addressRequest) {
        return new AddressResponse(addressRepository.save(
                addressMapper.addressRequestToAddress(addressRequest)));
    }

    @Override
    public AddressResponse getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) return new AddressResponse(addressOptional.get());
        throw new IntegrityValidation("address not found");
    }

    @Override
    public Page<AddressResponse> getAllAddress(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return addressRepository.findAll(pageable).map(AddressResponse::new);
    }

    @Override
    public AddressResponse updateAddress(UpdateAddress updateAddress) {
        Optional<Address> addressOptional = addressRepository.findById(updateAddress.id());
        if (addressOptional.isPresent()){
            return new AddressResponse(addressRepository.save(
                    addressMapper.addressUpdateToAddress(updateAddress)));
        }
        throw new IntegrityValidation("address not found");
    }

    @Override
    public void deleteAddress(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (!addressOptional.isPresent()) throw new IntegrityValidation("address not found");
        addressRepository.deleteById(id);
    }

}