package com.example.application.api;

import com.example.application.domain.Customer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 CustomerApi.java
 Author: Timothy Lombard (220154856)
 Date: 18st September (last updated) 2023
 */
@RestController
public class CustomerApi {
        static RestTemplate restTemplate = new RestTemplate();
        private static String urlPizzeria = "http://localhost:8080/customer";


        public Customer readCustomer(String id){
            Customer p = restTemplate.getForObject(urlPizzeria + "/read/" + id, Customer.class);
            System.out.println(p.toString());
            return p;
        }
    }
