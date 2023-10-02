package com.example.application.factory;

import com.example.application.domain.Bill;
import com.example.application.domain.Order;
import com.example.application.domain.OrderLine;
import com.example.application.domain.Pizza;
import com.example.application.util.Helper;

/*
OrderLineFactory.java
Author: Tamryn Lisa Lewin (219211981)
Date: 04 April 2023
Last update: 30 September 2023
 */

public class OrderLineFactory {
    public static OrderLine buildOrderLine(int quantity, Order order, Pizza pizza, Bill bill) {
        if(Helper.isNullOrEmpty(String.valueOf(quantity)) || Helper.isNullOrEmpty(String.valueOf(order)) || Helper.isNullOrEmpty(String.valueOf(pizza)) || Helper.isNullOrEmpty(String.valueOf(bill))) {
            return null;
        }

        Integer orderLineId = Helper.generateId2();

        OrderLine orderLine = new OrderLine.Builder()
                .setOrderLineId(orderLineId)
                .setQuantity(quantity)
                .setOrder(order)
                .setPizza(pizza)
                .setBill(bill)
                .build();
        return orderLine;
    }
}
