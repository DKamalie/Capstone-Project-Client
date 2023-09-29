package com.example.application.api;

import com.example.application.domain.Bill;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 BillApi.java
 Author: Vuyisa Lutho Mqoboli (219191018)
 Date: 25 September (last updated) 2023
 */
@RestController
public class BillApi {

    public Integer billId = 0;
    public double totalBill = 0.0;
    static RestTemplate restTemplate = new RestTemplate();
    private static String urlBill = "http://localhost:8080/bill/";

    public Bill createBill(Bill bill) {
        billId = bill.getBillId();
        totalBill = bill.getTotalBill();

        HttpEntity<Bill> request = new HttpEntity<>(new Bill(
                billId,
                totalBill
                ));
        Bill bill1 = restTemplate.postForObject(urlBill+ "/create", request, Bill.class);
        System.out.println(bill.toString());

        return bill1;
    }

    public Bill readBill(Integer id){
        Bill billId = restTemplate.getForObject(urlBill + "read/" + id, Bill.class);
        System.out.println(billId.toString());

        return billId;
    }

    public String updateBill(Bill bill){
        billId = bill.getBillId();
        totalBill = bill.getTotalBill();

        HttpEntity<Bill> request = new HttpEntity<>(new Bill(
                billId,
                totalBill
                ));
        Bill bill2 = restTemplate.postForObject(urlBill+ "update", request, Bill.class);
        System.out.println(bill2.toString());

        String update = "The updated Pizzeria is: " + bill2;
        return update;
    }

    public void deleteBill(Integer id) {
        String entityUrl = urlBill + "delete/" + id;
        System.out.println(entityUrl);
        restTemplate.delete(entityUrl);
    }
    public Set<Bill> getAllBill() {
        String apiUrl = urlBill + "getAll";
        ResponseEntity<Bill[]> response1 = restTemplate.getForEntity(apiUrl, Bill[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Bill[] bill = response1.getBody();
            return new HashSet<>(Arrays.asList(bill));
        } else {

            return Collections.emptySet();
        }
    }
}
