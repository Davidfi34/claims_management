package com.claims_management.address.service;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.UpdateAddress;
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

    public AddressServiceImp(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressResponse save(AddressRequest addressRequest) {
        Address address = new Address(
                null,addressRequest.street(),addressRequest.number(),
                addressRequest.apartment_number(),addressRequest.city(),
                addressRequest.province(),addressRequest.postal_code());
        return new AddressResponse(addressRepository.save(address));
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
            Address address = new Address(
                    updateAddress.id(),updateAddress.street(),updateAddress.number(),
                    updateAddress.apartment_number(),updateAddress.city(),updateAddress.province(),
                    updateAddress.postal_code());
            return new AddressResponse(addressRepository.save(address));
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