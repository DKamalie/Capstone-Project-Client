package com.example.application.domain;

/*
CustomerAddressId.java
CustomerAddressId entity
Author: Tamryn Lisa Lewin (219211981)
Date: 23 July 2023
Last update: July 2023
 */

import java.io.Serializable;
import java.util.Objects;

public class CustomerAddressId implements Serializable {

    private Customer customerID;
    private Address addressId;

    protected CustomerAddressId() {

    }

    public CustomerAddressId(Customer customerID, Address addressId) {
        this.customerID = customerID;
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAddressId that = (CustomerAddressId) o;
        return Objects.equals(customerID, that.customerID) && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, addressId);
    }
}
