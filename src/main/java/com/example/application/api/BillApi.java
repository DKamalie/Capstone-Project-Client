package com.example.application.api;

import com.example.application.domain.Bill;
import com.vaadin.flow.server.VaadinSession;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
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
    private String token = "";
    public String setToken(){
        AuthenticateUserApi i = new AuthenticateUserApi();
        token = i.getToken(
                "bouali.social@gmail.com",
                "password"
        );

        return token;
    }

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

    public Bill createBillAuth(Bill bill) throws IOException {
        billId = bill.getBillId();
        totalBill = bill.getTotalBill();
        bill = null;

        HttpEntity<Bill> request = new HttpEntity<>(new Bill(
                billId,
                totalBill
        ));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost get = new HttpPost("http://localhost:8083/api/v1/auth/pizzeria/create");
        get.setHeader("Authorization", "Bearer " + token);

        HttpResponse response = httpclient.execute(get);
        System.out.printf(Arrays.toString(response.getHeaders("Authorization")));
        System.out.println(response.getStatusLine());
        System.out.println("get:" + get);
        System.out.println(response.getEntity());

        return bill;
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

    public void deleteBillAuth(Integer billId) throws IOException {
        String token = String.valueOf(VaadinSession.getCurrent().getAttribute("token"));
        System.out.println("token: " +token);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpDelete get = new HttpDelete("http://localhost:8080/api/v1/auth/bill/delete/" + billId);
        get.setHeader("Authorization", "Bearer " + token);

        HttpResponse response = httpclient.execute(get);
        System.out.printf(Arrays.toString(response.getHeaders("Authorization")));
        System.out.println(response.getStatusLine());
        System.out.println("get:" + get);
        System.out.println(response.getEntity());

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
