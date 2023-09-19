package com.example.application.api;

import com.example.application.domain.Address;
import com.example.application.domain.Customer;
import com.example.application.domain.Pizzeria;
import org.jsoup.Connection;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 CustomerApi.java
 Author: Timothy Lombard (220154856)
 Date: 18st September (last updated) 2023
 */
@RestController
public class CustomerApi {
        static RestTemplate restTemplate = new RestTemplate();
        private static String urlCustomer = "http://localhost:8080/customer";

    public Customer createCustomer(Customer customer) {
        HttpEntity<Customer> request = new HttpEntity<>(customer);
        customer = restTemplate.postForObject(urlCustomer+ "/create", request, Customer.class);
        System.out.println(customer.toString());

        return customer;
    }
        public Customer readCustomer(String id){
            Customer p = restTemplate.getForObject(urlCustomer + "/read/" + id, Customer.class);
            System.out.println(p.toString());

            return p;
        }

        //public Customer updateCustomer(String id){
//        Customer p = restTemplate.delete(urlCustomer,id,Customer.class);
//        System.out.println(p.toString());
//        return p;
//    }

    public void deleteCustomer(String id) {
        String entityUrl = urlCustomer + "/delete/" + id;
        restTemplate.delete(entityUrl);
    }
    public Set<Customer> getAllPizzeria() {
        String apiUrl = urlCustomer + "/getAll";
        ResponseEntity<Customer[]> response1 = restTemplate.getForEntity(apiUrl, Customer[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Customer[] customers = response1.getBody();
            return new HashSet<>(Arrays.asList(customers));
        } else {

            return Collections.emptySet();
        }
    }
}
