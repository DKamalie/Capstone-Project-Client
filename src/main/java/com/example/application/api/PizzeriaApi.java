package com.example.application.api;

import com.example.application.domain.Pizzeria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 PizzeriaApi.java
 Author: Timothy Lombard (220154856)
 Date: 18st September (last updated) 2023
 */
@RestController
public class PizzeriaApi {
    static RestTemplate restTemplate = new RestTemplate();
//    @Autowired
//    private RestTemplate restTemplate2;
    private static String urlPizzeria = "http://localhost:8080/pizzeria";

    @GetMapping("/api/ping")
    public String ping() {
        System.out.println("Received ping request");
        return "pong";
    }

    public Pizzeria readPizzeria(String id){
        Pizzeria p = restTemplate.getForObject(urlPizzeria + "/read/" + id, Pizzeria.class);
        System.out.println(p.toString());
    return p;
    }
}
