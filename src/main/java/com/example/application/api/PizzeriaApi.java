package com.example.application.api;


import com.example.application.domain.Pizzeria;
import com.example.application.factory.PizzeriaFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 PizzeriaApi.java
 Author: Timothy Lombard (220154856)
 Date: 18st September (last updated) 2023
 */
@RestController
public class PizzeriaApi {
    static RestTemplate restTemplate = new RestTemplate();
    public String pizzriaAlias = "";
    public String location = "";
    public Integer id = 0;
    private static String urlPizzeria = "http://localhost:8080/pizzeria/";

    private static Pizzeria pizzeriaTest = PizzeriaFactory.buildPizzaria(
            "Hill Crest Work",
            "Hotel Transylvania");

    @GetMapping("/api/ping")
    public String ping() {
        System.out.println("Received ping request");
        return "pong";
    }

    public Pizzeria createPizzeria(Pizzeria pizzeria) {
        pizzriaAlias = pizzeria.getPizzariaAlias();
        location = pizzeria.getLocation();
        id = pizzeria.getPizzeriaID();

        HttpEntity<Pizzeria> request = new HttpEntity<>(new Pizzeria(
                id,
                pizzriaAlias,
                location));
        Pizzeria pizzeria2 = restTemplate.postForObject(urlPizzeria+ "create", request, Pizzeria.class);
        System.out.println(pizzeria.toString());

        return pizzeria2;
    }
    public Pizzeria readPizzeria(Integer id){
        Pizzeria p = restTemplate.getForObject(urlPizzeria + "read/" + id, Pizzeria.class);
        System.out.println(p.toString());
        return p;
    }

    public String updatePizzeria(Pizzeria pizzeria){
        pizzriaAlias = pizzeria.getPizzariaAlias();
        location = pizzeria.getLocation();
        id = pizzeria.getPizzeriaID();

        HttpEntity<Pizzeria> request = new HttpEntity<>(new Pizzeria(
                id,
                pizzriaAlias,
                location));
        Pizzeria pizzeria2 = restTemplate.postForObject(urlPizzeria+ "update", request, Pizzeria.class);
        System.out.println(pizzeria2.toString());

        String update = "The updated Pizzeria is: " + pizzeria2;
        return update;
    }

//    public void deleteBill(Integer id) {
//        String entityUrl = urlPizzeria + "/delete/" + id;
//        System.out.println(entityUrl);
//        restTemplate.delete(entityUrl);
//    }
    public Set<Pizzeria> getAllPizzeria() {
        String apiUrl = urlPizzeria + "/getAll";
        ResponseEntity<Pizzeria[]> response1 = restTemplate.getForEntity(apiUrl, Pizzeria[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Pizzeria[] pizzerias = response1.getBody();
            return new HashSet<>(Arrays.asList(pizzerias));
        } else {

            return Collections.emptySet();
        }
    }
}
