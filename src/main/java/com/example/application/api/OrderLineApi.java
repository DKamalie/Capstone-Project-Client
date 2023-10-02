package com.example.application.api;

import com.example.application.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
OrderLineApi.java
Author: Tamryn Lisa Lewin (219211981)
Date: 30 September 2023
Last update: 30 September 2023
 */

public class OrderLineApi {

    private Integer orderLineId;
    private int quantity;
    private Order order;
    private Pizza pizza;
    private Bill bill;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String orderLineServerURL = "http://localhost:8080/orderline";

    public OrderLine createOrderLine(OrderLine orderLine) {
        orderLineId = orderLine.getOrderLineId();
        quantity = orderLine.getQuantity();
        order = orderLine.getOrder();
        pizza = orderLine.getPizza();
        bill = orderLine.getBill();

        HttpEntity<OrderLine> request = new HttpEntity<>(new OrderLine(orderLineId, quantity, order, pizza, bill));
        OrderLine createdOrderLine = restTemplate.postForObject(orderLineServerURL + "/create", request, OrderLine.class);
        System.out.println(orderLine.toString());
        return createdOrderLine;
    }

    public OrderLine readOrderLine(Integer orderLineId) {
        OrderLine readOrderLine = restTemplate.getForObject(orderLineServerURL + "/read/" + orderLineId, OrderLine.class);
        System.out.println(readOrderLine.toString());
        return readOrderLine;
    }

    public String updateOrderLine(OrderLine orderLine) {
        orderLineId = orderLine.getOrderLineId();
        quantity = orderLine.getQuantity();
        order = orderLine.getOrder();
        pizza = orderLine.getPizza();
        bill = orderLine.getBill();

        HttpEntity<OrderLine> request = new HttpEntity<>(new OrderLine(orderLineId, quantity, order, pizza, bill));
        OrderLine updatedOrderLine = restTemplate.postForObject(orderLineServerURL + "/update", request, OrderLine.class);
        System.out.println(updatedOrderLine.toString());

        String update = "Updated OrderLine: " + updatedOrderLine;
        return update;
    }

    public Set<OrderLine> getAllOrderLines() {
        String orderLineApi = orderLineServerURL + "/getAll";
        ResponseEntity<OrderLine[]> response1 = restTemplate.getForEntity(orderLineApi, OrderLine[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            OrderLine[] orderLines = response1.getBody();
            return new HashSet<>(Arrays.asList(orderLines));
        } else {
            return Collections.emptySet();
        }
    }
}
