package com.example.application.factory;

import com.example.application.domain.Address;
import com.example.application.domain.AddressType;
import com.example.application.util.Helper;

/*
AddressFactory.java
Author: Tamryn Lisa Lewin (219211981)
Date: 04 April 2023
Last update: 30 September 2023
 */

public class AddressFactory {

    //  flat building
    public static Address buildAddress(String streetNumber, String streetName, String flatNumber, String suburb, String city, String province, String country, String postalCode, AddressType addressType) {
        if(Helper.isNullOrEmpty(streetNumber) || Helper.isNullOrEmpty(streetName) || Helper.isNullOrEmpty(flatNumber) || Helper.isNullOrEmpty(suburb) || Helper.isNullOrEmpty(city) || Helper.isNullOrEmpty(province) || Helper.isNullOrEmpty(country) || Helper.isNullOrEmpty(postalCode) || Helper.isNullOrEmpty(String.valueOf(addressType))) {
            return null;
        }

        postalCodeCheck(postalCode);

        Integer addressId = Helper.generateId2();

        Address address = new Address.Builder()
                .setAddressId(addressId)
                .setStreetNumber(streetNumber)
                .setStreetName(streetName)
                .setFlatNumber(flatNumber)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setCountry(country)
                .setPostalCode(postalCode)
                .setAddressType(addressType)
                .build();
        return address;
    }

    //  residential home
    public static Address buildAddress(String streetNumber, String streetName, String suburb, String city, String province, String country, String postalCode, AddressType addressType) {
        if(Helper.isNullOrEmpty(streetNumber) || Helper.isNullOrEmpty(streetName) || Helper.isNullOrEmpty(suburb) || Helper.isNullOrEmpty(city) || Helper.isNullOrEmpty(province) || Helper.isNullOrEmpty(country) || Helper.isNullOrEmpty(postalCode) || Helper.isNullOrEmpty(String.valueOf(addressType))) {
            return null;
        }

        postalCodeCheck(postalCode);

        Integer addressId = Helper.generateId2();

        Address address = new Address.Builder()
                .setAddressId(addressId)
                .setStreetNumber(streetNumber)
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setCountry(country)
                .setPostalCode(postalCode)
                .setAddressType(addressType)
                .build();
        return address;
    }

    private static void postalCodeCheck(String postalCode) {
        if (postalCode != null && postalCode.matches("^[0-9]{4}$")) {
//            int postalCodeValue = Integer.parseInt(postalCode);
            if (Integer.parseInt(postalCode) >= 1 && Integer.parseInt(postalCode) <= 9999) {
            } else {
                throw new IllegalArgumentException("Invalid postal code: Not between 1 and 9999");
            }
        } else {
            throw new IllegalArgumentException("Invalid postal code: Not 4 digits");
        }
    }
}
