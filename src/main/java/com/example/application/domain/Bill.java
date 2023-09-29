package com.example.application.domain;

import java.io.Serializable;
import java.util.Objects;

/*
Bill.java
Bill entity
Author: Vuyisa Lutho Mqoboli (219191018)
Date: 04 April 2023
 */

public class Bill implements Serializable {

    private Integer billId;
    private double totalBill;



    protected Bill(){

    }
    public Bill(Integer billId, double totalBill) {
        this.billId = billId;
        this.totalBill = totalBill;
    }

    private Bill(Builder builder){
        this.billId = builder.billId;
        this.totalBill = builder.totalBill;

    }
    public Integer getBillId() {
        return billId;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill bill)) return false;
        return Double.compare(bill.totalBill, totalBill) == 0 && billId.equals(bill.billId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, totalBill);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", totalBill=" + totalBill +
                '}';
    }

    public static class Builder{
        private Integer billId;
        private double totalBill;

        public Builder setBillId(Integer billId) {
            this.billId = billId;
            return this;
        }

        public Builder setTotalBill(double totalBill) {
            this.totalBill = totalBill;
            return this;
        }


        public Builder copy(Bill bill){
            this.billId = bill.billId;
            this.totalBill = bill.totalBill;
            return this;
        }
        public Bill build(){
            return new Bill(this);
        }
    }
}


