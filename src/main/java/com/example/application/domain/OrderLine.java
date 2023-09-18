package com.example.application.domain;

import java.util.Objects;

/*
OrderLine.java
OrderLine entity
Author: Tamryn Lisa Lewin (219211981)
Date: 04 April 2023
Last update: 31 July 2023
 */

public class OrderLine {

    private String orderLineId;
    private int quantity;
    private Order order;
    private Pizza pizza;
    private Bill bill;

    protected OrderLine() {}

    private OrderLine(Builder builder) {
        this.orderLineId = builder.orderLineId;
        this.quantity = builder.quantity;
        this.order = builder.order;
        this.pizza = builder.pizza;
        this.bill = builder.bill;
    }

    public String getOrderLineId() {
        return orderLineId;
    }
    public int getQuantity() {
        return quantity;
    }
    public Order getOrder() {
        return order;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public Bill getBill() {
       return bill;
    }

    public static class Builder {
        private String orderLineId;
        private int quantity;
        private Order order;
        private Pizza pizza;
        private Bill bill;

        public Builder setOrderLineId(String orderLineId) {
            this.orderLineId = orderLineId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }
        public Builder setPizza(Pizza pizza) {
            this.pizza = pizza;
            return this;
        }

        public Builder setBill(Bill bill) {
            this.bill = bill;
           return this;
       }

        public Builder copy(OrderLine orderLine) {
            this.orderLineId = orderLine.orderLineId;
            this.quantity = orderLine.quantity;
            this.order = orderLine.order;
            this.pizza = orderLine.pizza;
            this.bill = orderLine.bill;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity && Objects.equals(orderLineId, orderLine.orderLineId) && Objects.equals(order, orderLine.order) && Objects.equals(pizza, orderLine.pizza) && Objects.equals(bill, orderLine.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderLineId, quantity, order, pizza, bill);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId='" + orderLineId + '\'' +
                ", quantity=" + quantity +
                ", order=" + order +
                ", pizza=" + pizza +
                ", bill=" + bill +
                '}';
    }
}
