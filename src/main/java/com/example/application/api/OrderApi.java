package com.example.application.api;

import com.example.application.domain.Customer;
import com.example.application.domain.Order;
import com.example.application.domain.Pizzeria;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OrderApi {

    public enum OrderStatus{
        NEW, HOLD, SHIPPED, DELIVERED, CLOSED
    }

    private Integer orderId;
    private LocalDate createDate;
    private LocalTime time;
    private Customer customer;
    private Order.OrderStatus orderStatus;
    private Pizzeria pizzeria;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String orderServerURL = "http://localhost:8080/order";

    public Order createOrder(Order order) {
        orderId = order.getOrderId();
        createDate = order.getCreateDate();
        time = order.getTime();
        customer = order.getCustomer();
        orderStatus = order.getOrderStatus();
        pizzeria = order.getPizzeria();

        HttpEntity<Order> request = new HttpEntity<>(new Order(orderId, createDate, time, customer, orderStatus, pizzeria));
        Order createdOrder = restTemplate.postForObject(orderServerURL + "/create", request, Order.class);
        System.out.println(order.toString());
        return createdOrder;
    }

    public Order readOrder(Integer orderId) {
        Order readOrder = restTemplate.getForObject(orderServerURL + "/read/" + orderId, Order.class);
        System.out.println(readOrder.toString());
        return readOrder;
    }

    public String updateOrder(Order order) {
        orderId = order.getOrderId();
        createDate = order.getCreateDate();
        time = order.getTime();
        customer = order.getCustomer();
        orderStatus = order.getOrderStatus();
        pizzeria = order.getPizzeria();

        HttpEntity<Order> request = new HttpEntity<>(new Order(orderId, createDate, time, customer, orderStatus, pizzeria));
        Order updatedOrder = restTemplate.postForObject(orderServerURL + "/update", request, Order.class);
        System.out.println(order.toString());
        String update = "Updated Order: " + updatedOrder;
        return update;
    }

    public void deleteOrder(Integer orderId) {
        String deletedOrder = orderServerURL + "/delete/" + orderId;
        System.out.println(deletedOrder);
        restTemplate.delete(deletedOrder);
    }

    public Set<Order> getAllOrders() {
        String allOrders = orderServerURL + "/getall";
        ResponseEntity<Order[]> response1 = restTemplate.getForEntity(allOrders, Order[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Order[] orders = response1.getBody();
            return new HashSet<>(Arrays.asList(orders));
        } else {
            return Collections.emptySet();
        }
    }
}
