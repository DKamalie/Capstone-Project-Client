package com.example.application.api;

import com.example.application.domain.Chef;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ChefApi {

    static RestTemplate restTemplate = new RestTemplate();
    private static String urlChef = "http://localhost:8080/chef";

    public Set<Chef> getAllChef() {
        String apiUrl = urlChef + "/getall";
        ResponseEntity<Chef[]> response = restTemplate.getForEntity(apiUrl, Chef[].class);
        if(response.getStatusCode().is2xxSuccessful()) {
            Chef[] chefs = response.getBody();
            return new HashSet<>(Arrays.asList(chefs));
        }else {
            return Collections.emptySet();
        }
    }

}
