package com.example.application.api;

import com.example.application.domain.Customer;
import com.example.application.domain.Pizza;
import com.example.application.domain.Pizzeria;
import com.example.application.domain.StagedOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@RestController
public class StagedOrderApi {
    static RestTemplate restTemplate = new RestTemplate();
    private Integer orderId;
    private LocalDate createDate;
    private LocalTime time;
    private Customer customer;
    private String quantity;

    private Pizza pizza;
    private double total;
    private StagedOrder.OrderStatus orderStatus;
    private Pizzeria pizzeria;
    private static String urlPizzeria = "http://localhost:8080/stagedOrder/";


    public StagedOrder createStagedOrder(StagedOrder stagedOrder) {
        orderId = stagedOrder.getOrderId();
        createDate = stagedOrder.getCreateDate();
        time = stagedOrder.getTime();
        customer = stagedOrder.getCustomer();
        quantity = stagedOrder.getQuantity();
        pizza = stagedOrder.getPizza();
        total = stagedOrder.getTotal();
        orderStatus = stagedOrder.getOrderStatus();
        pizzeria = stagedOrder.getPizzeria();


        HttpEntity<StagedOrder> request = new HttpEntity<>(new StagedOrder(
                orderId,
                createDate,
                time,
                customer,
                quantity,
                pizza,
                total,
                orderStatus,
                pizzeria
        ));
        StagedOrder stagedOrder1 = restTemplate.postForObject(urlPizzeria+ "create", request, StagedOrder.class);
        System.out.println(pizzeria.toString());

        return stagedOrder1;
    }
    public StagedOrder readStagedOrder(Integer id){
        StagedOrder p = restTemplate.getForObject(urlPizzeria + "read/" + id, StagedOrder.class);
        System.out.println(p.toString());
        return p;
    }

    public String updateStagedOrder(StagedOrder stagedOrder){
        orderId = stagedOrder.getOrderId();
        createDate = stagedOrder.getCreateDate();
        time = stagedOrder.getTime();
        customer = stagedOrder.getCustomer();
        quantity = stagedOrder.getQuantity();
        pizza = stagedOrder.getPizza();
        total = stagedOrder.getTotal();
        orderStatus = stagedOrder.getOrderStatus();
        pizzeria = stagedOrder.getPizzeria();

        HttpEntity<StagedOrder> request = new HttpEntity<>(new StagedOrder(
                orderId,
                createDate,
                time,
                customer,
                quantity,
                pizza,
                total,
                orderStatus,
                pizzeria
        ));
        StagedOrder stagedOrder1 = restTemplate.postForObject(urlPizzeria+ "update", request, StagedOrder.class);
        System.out.println(stagedOrder1.toString());

        String update = "The updated Pizzeria is: " + stagedOrder1;
        return update;
    }

        public void deleteStagedOrder(Integer id) {
        String entityUrl = urlPizzeria + "/delete/" + id;
        System.out.println(entityUrl);
        restTemplate.delete(entityUrl);
    }
    public Set<StagedOrder> getAllPizzeria() {
        String apiUrl = urlPizzeria + "/getAll";
        ResponseEntity<StagedOrder[]> response1 = restTemplate.getForEntity(apiUrl, StagedOrder[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            StagedOrder[] stagedOrders = response1.getBody();
            return new HashSet<>(Arrays.asList(stagedOrders));
        } else {

            return Collections.emptySet();
        }
    }
}