package com.example.application.factory;

import com.example.application.domain.Customer;
import com.example.application.domain.Order;
import com.example.application.domain.Pizzeria;
import com.example.application.util.Helper;

import java.time.LocalDate;
import java.time.LocalTime;

/* OrderFactory.java
 Author: Timothy Lombard (220154856)
 Date: 30th July (last updated) 2023
*/

//    public class OrderFactory {
//        /*
//
//        public static Order buildOrder(LocalDate createDate, LocalTime time, Customer customer, Order.OrderStatus orderStatus, Pizzeria pizzeria) {
//            if (Helper.isNullOrEmpty(String.valueOf(Helper.isNullOrEmpty(String.valueOf(createDate)))) || Helper.isNullOrEmpty(String.valueOf(time))  || Helper.isNullOrEmpty(String.valueOf(customer)) || Helper.isNullOrEmpty(String.valueOf(orderStatus)) || Helper.isNullOrEmpty(String.valueOf(pizzeria)))  {
//                return null;
//            }
//
//            String orderId = Helper.generateId();
//
//            Order order = new Order.Builder().setOrderId(orderId).setCreateDate(createDate).setTime(time).setCustomer(customer).setOrderStatus(orderStatus).setPizzeria(pizzeria).build();
//            return order;
//
//        }
//
//         */
//
//        public static Order createOrder(LocalDate createDate, LocalTime time, Customer customer, Order.OrderStatus orderStatus, Pizzeria pizzeria) {
//            if (Helper.isNullOrEmpty(String.valueOf(Helper.isNullOrEmpty(String.valueOf(createDate)))) || Helper.isNullOrEmpty(String.valueOf(time))  || Helper.isNullOrEmpty(String.valueOf(customer)) || Helper.isNullOrEmpty(String.valueOf(orderStatus)) || Helper.isNullOrEmpty(String.valueOf(pizzeria)))  {
//                return null;
//            }
//
//            String orderId = Helper.generateId();
//
//            Order order = new Order.Builder().setOrderId(orderId).setCreateDate(createDate).setTime(time).setCustomer(customer).setOrderStatus(orderStatus).setPizzeria(pizzeria).build();
//            return order;
//
//        }
//
//    }

