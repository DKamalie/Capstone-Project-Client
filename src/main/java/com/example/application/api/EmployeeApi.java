package com.example.application.api;

import com.example.application.domain.Chef;
import com.example.application.domain.Driver;
import com.example.application.domain.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class EmployeeApi {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String employeeServerUrl = "http://localhost:8080/employee";

    public Set<Employee> getAllEmployee() {
        String apiUrl = employeeServerUrl + "/getall";
        ResponseEntity<Employee[]> response1 = restTemplate.getForEntity(apiUrl, Employee[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Employee[] employees = response1.getBody();
            return new HashSet<>(Arrays.asList(employees));
        } else {

            return Collections.emptySet();
        }
    }

    private final String driverServerUrl = "http://localhost:8080/driver";

    public Set<Driver> getAllDriver() {
        String apiUrl = driverServerUrl + "/getall";
        ResponseEntity<Driver[]> response1 = restTemplate.getForEntity(apiUrl, Driver[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Driver[] drivers = response1.getBody();
            return new HashSet<>(Arrays.asList(drivers));
        } else {

            return Collections.emptySet();
        }
    }

    private final String chefServerUrl = "http://localhost:8080/chef";

    public Set<Chef> getAllChef() {
        String apiUrl = chefServerUrl + "/getall";
        ResponseEntity<Chef[]> response1 = restTemplate.getForEntity(apiUrl, Chef[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Chef[] chefs = response1.getBody();
            return new HashSet<>(Arrays.asList(chefs));
        } else {

            return Collections.emptySet();
        }
    }




}
