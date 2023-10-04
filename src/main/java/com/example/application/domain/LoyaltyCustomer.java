package com.example.application.domain;

import com.example.application.domain.Customer;

import java.time.LocalDate;
import java.util.Objects;

/*
LoyaltyCustomer.java
LoyaltyCustomer entity
Author: Vuyisa Lutho Mqoboli (219191018)
Date: 04 April 2023
 */

public class LoyaltyCustomer extends Customer {

    private LocalDate dateJoined;
    private double discounts;
    private String password;
    private String email;

    protected LoyaltyCustomer(){

    }
    public LoyaltyCustomer(Integer customerID,String customerName,String customerSurname,String phoneNumber,Address address, LocalDate dateJoined, double discounts, String password, String email){
        super(customerID,customerName,customerSurname,phoneNumber,address);
       this.dateJoined = dateJoined;
       this.discounts = discounts;
       this.password = password;
       this.email = email;
    }

    private LoyaltyCustomer(Builder builder){
        super(builder);
        this.dateJoined = builder.dateJoined;
        this.discounts = builder.discounts;
        this.password = builder.password;
        this.email = builder.email;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public double getDiscounts() {
        return discounts;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder extends Customer.Builder{
        private LocalDate dateJoined;
        private double discounts;
        private String password;
        private String email;

        public Builder setDateJoined(LocalDate dateJoined) {
            this.dateJoined = dateJoined;
            return this;
        }

        public Builder setDiscounts(double discounts) {
            this.discounts = discounts;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }


        public Builder copy(LoyaltyCustomer loyaltyCustomer){
            super.copy(loyaltyCustomer);
            this.dateJoined = loyaltyCustomer.dateJoined;
            this.discounts = loyaltyCustomer.discounts;
            this.password = loyaltyCustomer.password;
            this.email = loyaltyCustomer.email;
            return this;
        }
        public LoyaltyCustomer build(){
            return new LoyaltyCustomer(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LoyaltyCustomer that = (LoyaltyCustomer) o;
        return Double.compare(that.discounts, discounts) == 0 && Objects.equals(dateJoined, that.dateJoined);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateJoined, discounts);
    }

    @Override
    public String toString() {
        return "LoyaltyCustomer{" +
                "dateJoined=" + dateJoined +
                ", discounts=" + discounts +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}

