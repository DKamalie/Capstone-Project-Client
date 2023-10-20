package com.example.application.api;

import com.example.application.domain.Base;
import com.example.application.domain.Pizza;
import com.example.application.domain.Pizzeria;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@RestController
public class PizzaApi {
    static RestTemplate restTemplate = new RestTemplate();
    public Integer pizzaId;
    public Base baseId;
    public String name;
    public String description;
    public Pizza.Size size;
    public boolean vegetarianOrNot;
    public double price;

    public Pizzeria pizzeria;
    private static String urlPizza = "http://localhost:8080/pizza";


    public Pizza createPizza(Pizza pizza) {
          pizzaId = pizza.getPizzaId();
          baseId = pizza.getBase();
          name = pizza.getName();
          description = pizza.getDescription();
          Pizza.Size size = pizza.getSize();
          vegetarianOrNot = pizza.isVegetarianOrNot();
          price = pizza.getPrice();
          pizzeria = pizza.getPizzeria();

        HttpEntity<Pizza> request = new HttpEntity<>(new Pizza(
                pizzaId,
                baseId,
                name,
                description,
                size,
                vegetarianOrNot,
                price,
                pizzeria));
        Pizza pizza2 = restTemplate.postForObject(urlPizza+ "/create", request, Pizza.class);
        System.out.println(pizzeria.toString());

        return pizza2;
    }
    public Pizza readPizza(Integer pizzaId){
        Pizza p = restTemplate.getForObject(urlPizza + "/read/" + pizzaId, Pizza.class);
        System.out.println(p.toString());
        return p;
    }

    public String updatePizza(Pizza pizza){
        pizzaId = pizza.getPizzaId();
        baseId = pizza.getBase();
        name = pizza.getName();
        description = pizza.getDescription();
        Pizza.Size size = pizza.getSize();
        vegetarianOrNot = pizza.isVegetarianOrNot();
        price = pizza.getPrice();
        pizzeria = pizza.getPizzeria();

        HttpEntity<Pizza> request = new HttpEntity<>(new Pizza(
                pizzaId,
                baseId,
                name,
                description,
                size,
                vegetarianOrNot,
                price,
                pizzeria));
        Pizza pizza2 = restTemplate.postForObject(urlPizza+ "/update", request, Pizza.class);
        System.out.println(pizza2.toString());

        String update = "The updated pizza is: " + pizza2;
        return update;
    }

    public void deletePizza(Integer pizzaId) {
        String deletedPizza = urlPizza + "/delete/" + pizzaId;
        System.out.println(deletedPizza);
        restTemplate.delete(deletedPizza);
    }

    public Set<Pizza> getAllPizza() {
        String apiUrl = urlPizza + "/getall";
        ResponseEntity<Pizza[]> response1 = restTemplate.getForEntity(apiUrl, Pizza[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Pizza[] pizza= response1.getBody();
            return new HashSet<>(Arrays.asList(pizza));
        } else {
            return Collections.emptySet();
        }
    }
}
