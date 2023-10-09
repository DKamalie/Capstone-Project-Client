package com.example.application.api;

import com.example.application.domain.Address;
import com.example.application.domain.LoyaltyCustomer;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class LoyaltyCustomerApi {

    static RestTemplate restTemplate = new RestTemplate();
    public Integer customerId;
    public String customerName;
    public String customerSurname;

    public String phoneNumber;

    public Address address;
    public LocalDate dateJoined;
    public double discounts;

    public String password;

    public String email;


    private static String urlLoyaltyCustomer = "http://localhost:8080/loyaltycustomer/";

    public LoyaltyCustomer createLoyaltyCustomer(LoyaltyCustomer loyaltyCustomer) {
        customerId = loyaltyCustomer.getCustomerID();
        customerName = loyaltyCustomer.getCustomerName();
        customerSurname = loyaltyCustomer.getCustomerSurname();
        phoneNumber = loyaltyCustomer.getPhoneNumber();
        address = loyaltyCustomer.getAddress();
        dateJoined = loyaltyCustomer.getDateJoined();
        discounts = loyaltyCustomer.getDiscounts();
        password = loyaltyCustomer.getPassword();
        email = loyaltyCustomer.getEmail();

        HttpEntity<LoyaltyCustomer > request = new HttpEntity<>(new LoyaltyCustomer (
                customerId,
                customerName,
                customerSurname,
                phoneNumber,
                address,
                dateJoined,
                discounts,
                password,
                email
        ));
        LoyaltyCustomer loyaltyCustomer1 = restTemplate.postForObject(urlLoyaltyCustomer+ "/create", request, LoyaltyCustomer.class);
        System.out.println(loyaltyCustomer.toString());

        return loyaltyCustomer1;
    }

    public LoyaltyCustomer readLoyaltyCustomer(Integer id){
        LoyaltyCustomer customerId = restTemplate.getForObject(urlLoyaltyCustomer + "read/" + id, LoyaltyCustomer.class);
        System.out.println(customerId.toString());

        return customerId;
    }

    public String updateLoyaltyCustomer(LoyaltyCustomer loyaltyCustomer){
        customerId = loyaltyCustomer.getCustomerID();
        customerName = loyaltyCustomer.getCustomerName();
        customerSurname = loyaltyCustomer.getCustomerSurname();
        phoneNumber = loyaltyCustomer.getPhoneNumber();
        address = loyaltyCustomer.getAddress();
        dateJoined = loyaltyCustomer.getDateJoined();
        discounts = loyaltyCustomer.getDiscounts();
        password = loyaltyCustomer.getPassword();
        email = loyaltyCustomer.getEmail();

        HttpEntity<LoyaltyCustomer> request = new HttpEntity<>(new LoyaltyCustomer(
                customerId,
                customerName,
                customerSurname,
                phoneNumber,
                address,
                dateJoined,
                discounts,
                password,
                email
        ));
        LoyaltyCustomer loyaltyCustomer2 = restTemplate.postForObject(urlLoyaltyCustomer+ "update", request, LoyaltyCustomer.class);
        System.out.println(loyaltyCustomer2.toString());

        String update = "The updated loyalty customer is: " + loyaltyCustomer2;
        return update;
    }

    public void deleteLoyaltyCustomer(Integer id) {
        String entityUrl = urlLoyaltyCustomer + "delete/" + id;
        System.out.println(entityUrl);
        restTemplate.delete(entityUrl);
    }
    public Set<LoyaltyCustomer> getAllLoyaltyCustomer() {
        String apiUrl = urlLoyaltyCustomer + "getall";
        ResponseEntity<LoyaltyCustomer[]> response1 = restTemplate.getForEntity(apiUrl, LoyaltyCustomer[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            LoyaltyCustomer[] loyaltyCustomer = response1.getBody();
            return new HashSet<>(Arrays.asList(loyaltyCustomer));
        } else {

            return Collections.emptySet();
        }
    }

}
