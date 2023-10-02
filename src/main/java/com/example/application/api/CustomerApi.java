package com.example.application.api;

import com.example.application.domain.Address;
import com.example.application.domain.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
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
    public Integer id = 0;
    public String customerName = "";
    public String customerSurname = "";

    public String phoneNumber = "";

    public Address address = null;
        static RestTemplate restTemplate = new RestTemplate();
        private static String urlCustomer = "http://localhost:8080/customer/";

    public Customer createCustomer(Customer customer) {
        id = customer.getCustomerID();
        customerName = customer.getCustomerName();
        customerSurname = customer.getCustomerSurname();
        phoneNumber = customer.getPhoneNumber();
        address = customer.getAddress();

        HttpEntity<Customer> request = new HttpEntity<>(new Customer(
                id,
                customerName,
                customerSurname,
                phoneNumber,
                address));
        Customer customer2 = restTemplate.postForObject(urlCustomer+ "/create", request, Customer.class);
        System.out.println(customer.toString());

        return customer2;
    }
        public Customer readCustomer(Integer id){
            Customer p = restTemplate.getForObject(urlCustomer + "read/" + id, Customer.class);
            System.out.println("teken +" + p);
            System.out.println(p.toString());

            return p;
        }

        public String updateCustomer(Customer customer){
            id = customer.getCustomerID();
            customerName = customer.getCustomerName();
            customerSurname = customer.getCustomerSurname();
            phoneNumber = customer.getPhoneNumber();
            address = customer.getAddress();

        HttpEntity<Customer> request = new HttpEntity<>(new Customer(
                id,
                customerName,
                customerSurname,
                phoneNumber,
                address));
        Customer customer2 = restTemplate.postForObject(urlCustomer+ "update", request, Customer.class);
        System.out.println(customer2.toString());

        String update = "The updated Pizzeria is: " + customer2;
        return update;
    }

    public void deleteBill(Integer id) {
        String entityUrl = urlCustomer + "delete/" + id;
        System.out.println(entityUrl);
        restTemplate.delete(entityUrl);
    }
    public Set<Customer> getAllPizzeria() {
        String apiUrl = urlCustomer + "getAll";
        ResponseEntity<Customer[]> response1 = restTemplate.getForEntity(apiUrl, Customer[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Customer[] customers = response1.getBody();
            return new HashSet<>(Arrays.asList(customers));
        } else {

            return Collections.emptySet();
        }
    }
}
