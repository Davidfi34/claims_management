package com.claims_management.address.Dto.mapper;

import com.claims_management.address.Dto.AddressRequest;
import com.claims_management.address.Dto.AddressResponse;
import com.claims_management.address.Dto.UpdateAddress;
import com.claims_management.address.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address addressResponseToAddress(AddressResponse addressResponse){
        return new Address.AddressBuilder()
                .idAddress(addressResponse.id())
                .street(addressResponse.street())
                .number(addressResponse.number())
                .apartment_number(addressResponse.apartment_number())
                .city(addressResponse.city())
                .province(addressResponse.province())
                .postal_code(addressResponse.postal_code())
                .build();
    }


    public UpdateAddress addressToUpdateAddress(Address address){
        return new UpdateAddress(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getApartment_number(),
                address.getCity(),
                address.getProvince(),
                address.getPostal_code());
    }

    public Address addressRequestToAddress(AddressRequest addressRequest){
        return new Address.AddressBuilder()
                .street(addressRequest.street())
                .number(addressRequest.number())
                .apartment_number(addressRequest.apartment_number())
                .city(addressRequest.city())
                .province(addressRequest.province())
                .postal_code(addressRequest.postal_code())
                .build();
    }

    public Address addressUpdateToAddress(UpdateAddress updateAddress){
        return new Address.AddressBuilder()
                .idAddress(updateAddress.id())
                .street(updateAddress.street())
                .number(updateAddress.number())
                .apartment_number(updateAddress.apartment_number())
                .city(updateAddress.city())
                .province(updateAddress.province())
                .postal_code(updateAddress.postal_code())
                .build();
    }

    public Address addressBuild(Address address){
        return new Address.AddressBuilder()
                .idAddress(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .apartment_number(address.getApartment_number())
                .city(address.getCity())
                .province(address.getProvince())
                .postal_code(address.getPostal_code())
                .build();
    }
}
