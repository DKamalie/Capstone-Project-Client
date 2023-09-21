package com.example.application.domain;

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

    protected LoyaltyCustomer(){

    }

    private LoyaltyCustomer(Builder builder){
        super(builder);
        this.dateJoined = builder.dateJoined;
        this.discounts = builder.discounts;

    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }
    public double getDiscounts() {
        return discounts;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    public static class Builder extends Customer.Builder{
        private LocalDate dateJoined;
        private double discounts;

        public Builder setDateJoined(LocalDate dateJoined) {
            this.dateJoined = dateJoined;
            return this;
        }

        public Builder setDiscounts(double discounts) {
            this.discounts = discounts;
            return this;
        }


        public Builder copy(LoyaltyCustomer loyaltyCustomer){
            super.copy(loyaltyCustomer);
            this.dateJoined = loyaltyCustomer.dateJoined;
            this.discounts = loyaltyCustomer.discounts;
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
                "customerID=" + customerID +
                ", customerName=" + customerName +
                ", customerSurname='" + customerSurname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateJoined='" + dateJoined + '\'' +
                ", discounts=" + discounts +
                '}';
    }
}

