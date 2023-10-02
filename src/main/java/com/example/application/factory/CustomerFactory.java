package com.example.application.factory;


import com.example.application.domain.Address;
import com.example.application.domain.Customer;
import com.example.application.util.Helper;

/* CustomerFactory.java
   Entity for the CustomerFactory
   Author: Keenan Meyer (220194920)
   Date: 30th March 2023
*/
public class CustomerFactory {

    public static Customer buildCustomer(String customerName,
                                         String customerSurname,
                                         String phoneNumber,
                                         Address address) {
        if (Helper.isNullOrEmpty(customerName) || Helper.isNullOrEmpty(String.valueOf(customerSurname))|| Helper.isNullOrEmpty(phoneNumber)) {
            return null;
        }

        Integer customerID = Helper.generateId2();

        Customer customer = new Customer.Builder()
                .setCustomerID(customerID)
                .setCustomerName(customerName)
                .setCustomerSurname(customerSurname)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .build();
        return customer;

    }

    public static Customer buildCustomer(String customerName, String customerSurname,String phoneNumber) {
        if (Helper.isNullOrEmpty(customerName) || Helper.isNullOrEmpty(String.valueOf(customerSurname))|| Helper.isNullOrEmpty(phoneNumber)) {
            return null;
        }

        Integer customerID = Helper.generateId2();

        Customer customer = new Customer.Builder()
                .setCustomerID(customerID)
                .setCustomerName(customerName)
                .setCustomerSurname(customerSurname)
                .setPhoneNumber(phoneNumber)
                .build();
        return customer;

    }

    public static Customer buildCustomer(Integer customerID, String customerName, String customerSurname,String phoneNumber, Address address) {
        if (Helper.isNullOrEmpty(customerName) || Helper.isNullOrEmpty(String.valueOf(customerSurname))|| Helper.isNullOrEmpty(phoneNumber)) {
            return null;
        }
        Customer customer = new Customer.Builder()
                .setCustomerID(customerID)
                .setCustomerName(customerName)
                .setCustomerSurname(customerSurname)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .build();
        return customer;

    }
}
