package com.claims_management.address.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Table(name = "address")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String apartment_number;
    private String city;
    private String province;
    private String postal_code;


    public static final class AddressBuilder {
        private Long id;
        private String street;
        private String number;
        private String apartment_number;
        private String city;
        private String province;
        private String postal_code;

        public AddressBuilder() {
        }

        public AddressBuilder idAddress(Long id) {
            this.id = id;
            return this;
        }

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder number(String number) {
            this.number = number;
            return this;
        }

        public AddressBuilder apartment_number(String apartment_number) {
            this.apartment_number = apartment_number;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder province(String province) {
            this.province = province;
            return this;
        }

        public AddressBuilder postal_code(String postal_code) {
            this.postal_code = postal_code;
            return this;
        }


        public Address build() {
            Address a = new Address();
            a.setId(id);
            a.setStreet(street);
            a.setNumber(number);
            a.setApartment_number(apartment_number);
            a.setCity(city);
            a.setProvince(province);
            a.setPostal_code(postal_code);
            return a;
        }
    }
}
