package com.example.application.domain;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class StagedOrder {

    public enum OrderStatus{
        NEW, HOLD, SHIPPED, DELIVERED, CLOSED
    }

    private Integer orderId;
    private LocalDate createDate;
    private LocalTime time;
    private Customer customer;
    private String quantity;

    private Pizza pizza;
    private double total;
    private OrderStatus orderStatus;

    public StagedOrder(){

    }
    public StagedOrder(Integer orderId,
                       LocalDate createDate,
                       LocalTime time,
                       Customer customer,
                       String  quantity,
                       Pizza pizza,
                       double total,
                       OrderStatus orderStatus){
        this.orderId = orderId;
        this.createDate =createDate;
        this.time = time;
        this.customer =customer;
        this.quantity = quantity;
        this.pizza = pizza;
        this.total = total;
        this.orderStatus = orderStatus;
    }


    private StagedOrder(Builder builder){
        this.orderId = builder.orderId;
        this.createDate = builder.createDate;
        this.time = builder.time;
        this.customer = builder.customer;
        this.orderStatus = builder.orderStatus;
        this.quantity = builder.quantity;
        this.pizza = builder.pizza;
        this.total = builder.total;
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
    public Pizza getPizza() {
        return pizza;
    }
    public String getQuantity() {
        return quantity;
    }
    public double getTotal() {
        return total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public static class Builder {
        private Integer orderId;
        private LocalDate createDate;
        private LocalTime time;
        private Customer customer;
        private String quantity;
        private Pizza pizza;
        private double total;
        private OrderStatus orderStatus;


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

        public Builder setQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPizza(Pizza pizza){
            this.pizza = pizza;
            return this;
        }
        public Builder setTotal(double total){
            this.total = total;
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }


        public Builder copy(StagedOrder order) {
            this.orderId = order.orderId;
            this.createDate = order.createDate;
            this.time = order.time;
            this.customer = order.customer;
            this.total = order.total;
            this.orderStatus = order.orderStatus;
            this.quantity = order.quantity;
            return this;
        }

        public StagedOrder build() {
            return new StagedOrder(this);
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(orderId, createDate, time, customer, orderStatus);
    }

    @Override
    public String toString() {
        return "StagedOrder{" +
                "orderId=" + orderId +
                ", createDate=" + createDate +
                ", time=" + time +
                ", customer=" + customer +
                ", quantity='" + quantity + '\'' +
                ", pizza=" + pizza +
                ", total=" + total +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
