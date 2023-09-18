package com.example.application.domain;

/*
CustomerAddress.java
CustomerAddress entity
Author: Tamryn Lisa Lewin (219211981)
Date: 23 July 2023
Last update:  July 2023
 */

import java.util.Objects;


public class CustomerAddress {

    private Customer customerID;

    private Address addressId;

    protected CustomerAddress() {

    }

    private CustomerAddress(Builder builder) {
        this.customerID = builder.customerID;
        this.addressId = builder.addressId;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public Address getAddressId() {
        return addressId;
    }

    public static class Builder {
        private Customer customerID;
        private Address addressId;

        public Builder setCustomerID(Customer customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setAddressId(Address addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder copy(CustomerAddress customerAddress) {
            this.customerID = customerAddress.customerID;
            this.addressId = customerAddress.addressId;
            return this;
        }

        public CustomerAddress build() {
            return new CustomerAddress(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAddress that = (CustomerAddress) o;
        return Objects.equals(customerID, that.customerID) && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, addressId);
    }

    @Override
    public String toString() {
        return "CustomerAddress{" +
                "customerID=" + customerID +
                ", addressId=" + addressId +
                '}';
    }
}
