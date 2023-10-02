package com.example.application.api;

import com.example.application.domain.Address;
import com.example.application.domain.AddressType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
AddressApi.java
Author: Tamryn Lisa Lewin (219211981)
Date: 30 September 2023
Last update: 30 September 2023
 */

@RestController
public class AddressApi {

    private Integer addressId;
    private String streetNumber;
    private String streetName;
    private String flatNumber;
    private String suburb;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private AddressType addressType;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String addressServerURL = "http://localhost:8080/address";

    public Address createAddress(Address address) {
        addressId = address.getAddressId();
        streetNumber = address.getStreetNumber();
        streetName = address.getStreetName();
        flatNumber = address.getFlatNumber();
        suburb = address.getSuburb();
        city = address.getCity();
        province = address.getProvince();
        country = address.getCountry();
        postalCode = address.getPostalCode();
        addressType = address.getAddressType();

        HttpEntity<Address> request = new HttpEntity<>(new Address(addressId, streetNumber, streetName, flatNumber, suburb, city, province, country, postalCode, addressType));
        Address createdAddress = restTemplate.postForObject(addressServerURL + "/create", request, Address.class);
        System.out.println(address.toString());
        return createdAddress;
    }

    public Address readAddress(Integer addressId) {
        Address readAddress = restTemplate.getForObject(addressServerURL + "/read/" + addressId, Address.class);
        System.out.println(readAddress.toString());
        return readAddress;
    }

    public String updateAddress(Address address) {
        addressId = address.getAddressId();
        streetNumber = address.getStreetNumber();
        streetName = address.getStreetName();
        flatNumber = address.getFlatNumber();
        suburb = address.getSuburb();
        city = address.getCity();
        province = address.getProvince();
        country = address.getCountry();
        postalCode = address.getPostalCode();
        addressType = address.getAddressType();

        HttpEntity<Address> request = new HttpEntity<>(new Address(addressId, streetNumber, streetName, flatNumber, suburb, city, province, country, postalCode, addressType));
        Address updatedAddress = restTemplate.postForObject(addressServerURL+ "/update", request, Address.class);
        System.out.println(updatedAddress.toString());

        String update = "Updated Address: " + updatedAddress;
        return update;
    }

    public Set<Address> getAllAddresses() {
        String allAddresses = addressServerURL + "/getall";
        ResponseEntity<Address[]> response1 = restTemplate.getForEntity(allAddresses, Address[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Address[] addresses = response1.getBody();
            return new HashSet<>(Arrays.asList(addresses));
        } else {
            return Collections.emptySet();
        }
    }
}
