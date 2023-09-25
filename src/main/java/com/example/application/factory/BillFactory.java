package com.example.application.factory;



/*
BillFactory.java
Author: Vuyisa Lutho Mqoboli (219191018)
Date: 04 April 2023
 */

import com.example.application.domain.Bill;
import com.example.application.util.Helper;

public class BillFactory {
    public static Bill createBill(double totalBill){
        if(Helper.isNullOrEmpty(String.valueOf(totalBill))){
            return null;
        }

        String billId = Helper.generateId();

        Bill em = new Bill.Builder().setBillId(billId).setTotalBill(totalBill).build();

        return em;
    }
}
