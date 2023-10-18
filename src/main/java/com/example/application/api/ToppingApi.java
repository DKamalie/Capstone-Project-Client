package com.example.application.api;

import com.example.application.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ToppingApi {

    private Integer toppingId;
    private String name;
    private String description;
    private int quantity;
    private double price;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String toppingServerURL = "http://localhost:8080/topping";

    public Topping createTopping(Topping topping) {
        toppingId = topping.getToppingId();
        name = topping.getName();
        description = topping.getDescription();
        quantity = topping.getQuantity();
        price = topping.getPrice();

        HttpEntity<Topping> request = new HttpEntity<>(new Topping(toppingId, name, description, quantity, price));
        Topping createdTopping = restTemplate.postForObject(toppingServerURL + "/create", request, Topping.class);
        System.out.println(topping.toString());
        return createdTopping;
    }

    public Topping readTopping(Integer toppingId) {
        Topping readTopping = restTemplate.getForObject(toppingServerURL + "/read/" + toppingId, Topping.class);
        System.out.println(readTopping.toString());
        return readTopping;
    }

    public String updateTopping(Topping topping) {
        toppingId = topping.getToppingId();
        name = topping.getName();
        description = topping.getDescription();
        quantity = topping.getQuantity();
        price = topping.getPrice();

        HttpEntity<Topping> request = new HttpEntity<>(new Topping(toppingId, name, description, quantity, price));
        Topping updatedTopping = restTemplate.postForObject(toppingServerURL + "/update", request, Topping.class);
        System.out.println(topping.toString());

        String update = "Updated Topping: " + updatedTopping;
        return update;
    }

    public void deleteTopping(Integer toppingId) {
        String deletedTopping = toppingServerURL + "/delete/" + toppingId;
        System.out.println(deletedTopping);
        restTemplate.delete(deletedTopping);
    }

    public ArrayList<Topping> getAllToppings() {
        String allToppings = toppingServerURL + "/getall";
        ResponseEntity<Topping[]> response1 = restTemplate.getForEntity(allToppings, Topping[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Topping[] toppings = response1.getBody();
            return new ArrayList<>(Arrays.asList(toppings));
        } else {
            return new ArrayList<>();
        }
    }
}
