package com.example.application.api;

import com.example.application.domain.Chef;
import com.example.application.domain.Driver;
import com.example.application.domain.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ChefApi extends Employee {

    private String title;
    private String culinaryExperience;

    static RestTemplate restTemplate = new RestTemplate();
    private static String urlChef = "http://localhost:8080/chef";

    public Chef createChef(Chef chef) {

        empId = chef.getEmpId();
        name = chef.getName();
        surname = chef.getSurname();
        phoneNumber = chef.getPhoneNumber();
        email = chef.getEmail();
        pizzeria = chef.getPizzeria();
        title = chef.getTitle();
        culinaryExperience = chef.getCulinaryExperience();

        HttpEntity<Chef> request = new HttpEntity<>(new Chef(empId, name, surname, phoneNumber, email, pizzeria, title, culinaryExperience));
        Chef createdChef = restTemplate.postForObject(urlChef + "/create", request, Chef.class);
        System.out.println(chef.toString());
        return createdChef;
    }

    public Chef readChef(Integer employeeId) {
        Chef readChef = restTemplate.getForObject(urlChef + "/read/" + employeeId, Chef.class);
        System.out.println(readChef.toString());
        return readChef;
    }

    public String updateChef(Chef chef) {
        empId = chef.getEmpId();
        name = chef.getName();
        surname = chef.getSurname();
        phoneNumber = chef.getPhoneNumber();
        email = chef.getEmail();
        pizzeria = chef.getPizzeria();
        title = chef.getTitle();
        culinaryExperience = chef.getCulinaryExperience();

        HttpEntity<Chef> request = new HttpEntity<>(new Chef(empId, name, surname, phoneNumber, email, pizzeria, title, culinaryExperience));
        Chef updatedChef = restTemplate.postForObject(urlChef + "/update", request, Chef.class);
        System.out.println(chef.toString());

        String update = "Updated Chef: " + updatedChef;
        return update;
    }

    public void deleteChef(Integer employeeId) {
        String deletedChef = urlChef + "/delete/" + employeeId;
        System.out.println(deletedChef);
        restTemplate.delete(deletedChef);
    }

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
