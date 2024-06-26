package com.claims_management.address.service;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.UpdateAddress;
import org.springframework.data.domain.Page;


public interface AddressService {

    public AddressResponse save(AddressRequest addressRequest);
    public AddressResponse getAddressById(Long id);
    public Page<AddressResponse> getAllAddress(int numberPage);
    public AddressResponse updateAddress(UpdateAddress updateAddress);
    public void deleteAddress(Long id);
}
