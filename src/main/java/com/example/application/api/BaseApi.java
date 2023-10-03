package com.example.application.api;

import com.example.application.domain.Base;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class BaseApi {

    public enum BaseCrust{
        CRUSTY, NON_CRUSTY
    }

    public enum BaseThickness{
        THIN, THICK
    }
    public enum BaseTexture{
        LIGHT, CHEWY, DOUGHY, CRISPY, STRETCHY
    }



    private Integer baseId;
    private Base.BaseCrust crust;
    private Base.BaseThickness thickness;
    private Base.BaseTexture texture;
    private double price;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseServerURL = "http://localhost:8080/base";

    public Base createBase(Base base) {
        baseId = base.getBaseId();
        crust = base.getCrust();
        thickness = base.getThickness();
        texture = base.getTexture();
        price = base.getPrice();


        HttpEntity<Base> request = new HttpEntity<>(new Base(baseId, crust, thickness, texture, price));
        Base createdBase = restTemplate.postForObject(baseServerURL + "/create", request, Base.class);
        System.out.println(base.toString());
        return createdBase;
    }

    public Base readBase(Integer baseId) {
        Base readBase = restTemplate.getForObject(baseServerURL + "/read/" + baseId, Base.class);
        System.out.println(readBase.toString());
        return readBase;
    }

    public String updateBase(Base base) {
        baseId = base.getBaseId();
        crust = base.getCrust();
        thickness = base.getThickness();
        texture = base.getTexture();
        price = base.getPrice();

        HttpEntity<Base> request = new HttpEntity<>(new Base(baseId, crust, thickness, texture, price));
        Base updatedBase = restTemplate.postForObject(baseServerURL + "/update", request, Base.class);
        System.out.println(base.toString());
        String update = "Updated Base: " + updatedBase;
        return update;
    }

    public void deleteBase(Integer baseId) {
        String deletedBase = baseServerURL + "/delete/" + baseId;
        System.out.println(deletedBase);
        restTemplate.delete(deletedBase);
    }

    public ArrayList<Base> getAllBase() {
        String apiUrl = baseServerURL + "/getall";
        ResponseEntity<Base[]> response1 = restTemplate.getForEntity(apiUrl, Base[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Base[] bases = response1.getBody();
            return new ArrayList<>(Arrays.asList(bases));
        } else {

            return new ArrayList<>();
        }
    }


}
