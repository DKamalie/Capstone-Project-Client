package com.example.application.factory;

/*
CustomerAddressFactory.java
Author: Tamryn Lisa Lewin (219211981)
Date: 24 July 2023
Last update:  2023
 */

import com.example.application.domain.Address;
import com.example.application.domain.Customer;
import com.example.application.domain.CustomerAddress;
import com.example.application.util.Helper;

public class CustomerAddressFactory {

    public static CustomerAddress buildCustomerAddress(Customer customerID, Address addressId) {
        if (Helper.isNullOrEmpty(String.valueOf(customerID)) || Helper.isNullOrEmpty(String.valueOf(addressId))) {
            return null;
        }

        CustomerAddress customerAddress = new CustomerAddress.Builder()
                .setCustomerID(customerID)
                .setAddressId(addressId)
                .build();
        return customerAddress;
    }
}
