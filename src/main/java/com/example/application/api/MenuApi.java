package com.example.application.api;

import com.example.application.domain.Employee;
import com.example.application.domain.Pizza;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class MenuApi {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String pizzaUrl = "http://localhost:8080/pizza";

    public Set<Pizza> getAllPizzas(){
        String apiUrl = pizzaUrl + "/getall";
        ResponseEntity<Pizza[]> response = restTemplate.getForEntity(apiUrl, Pizza[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Pizza[] pizzas = response.getBody();
            return new HashSet<>(Arrays.asList(pizzas));
        }else {
            return Collections.emptySet();
        }
    }

}
