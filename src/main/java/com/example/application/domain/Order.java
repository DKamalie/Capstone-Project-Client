package com.example.application.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/* Order.java
 Entity for the Order
 Author: Timothy Lombard (220154856)
 Date: 4th August (last updated) 2023
 ////
*/

public class Order {

    public enum OrderStatus{
        NEW, HOLD, SHIPPED, DELIVERED, CLOSED
    }

    private Integer orderId;
    private LocalDate createDate;
    private LocalTime time;
    private Customer customer;
    private OrderStatus orderStatus;
    private Pizzeria pizzeria;

    protected Order(){

    }

    private Order(Builder builder){
        this.orderId = builder.orderId;
        this.createDate = builder.createDate;
        this.time = builder.time;
        this.customer = builder.customer;
        this.orderStatus = builder.orderStatus;
        this.pizzeria = builder.pizzeria;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    public static class Builder {
        private Integer orderId;
        private LocalDate createDate;
        private LocalTime time;
        private Customer customer;
        private OrderStatus orderStatus;
        private Pizzeria pizzeria;

        public Builder setOrderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setCreateDate(LocalDate createDate){
            this.createDate = createDate;
            return this;
        }

        public Builder setTime(LocalTime time) {
            this.time = time;
            return this;
        }

        public Builder setCustomer(Customer customer){
            this.customer = customer;
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder setPizzeria(Pizzeria pizzeria) {
            this.pizzeria = pizzeria;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.createDate = order.createDate;
            this.time = order.time;
            this.customer = order.customer;
            this.orderStatus = order.orderStatus;
            this.pizzeria = order.pizzeria;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(createDate, order.createDate) && Objects.equals(time, order.time) && Objects.equals(customer, order.customer) && orderStatus == order.orderStatus && Objects.equals(pizzeria, order.pizzeria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, createDate, time, customer, orderStatus, pizzeria);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", time=" + time +
                ", customer=" + customer +
                ", orderStatus=" + orderStatus +
                ", pizzeria=" + pizzeria +
                '}';
    }
}
