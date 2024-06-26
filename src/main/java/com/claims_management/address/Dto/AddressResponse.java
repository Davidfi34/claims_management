package com.claims_management.address.Dto;

import com.claims_management.address.models.Address;


public record AddressResponse(Long id, String street, String number, String apartment_number, String city,
                              String province, String postal_code ) {
    public AddressResponse(Address address){
        this(address.getId(), address.getStreet(), address.getNumber(),address.getApartment_number(),
                address.getCity(),address.getProvince(), address.getPostal_code());
    }
}

