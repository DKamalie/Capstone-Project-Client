package com.example.application.api;

import com.example.application.domain.Driver;
import com.example.application.domain.Employee;
import com.example.application.domain.Vehicle;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DriverApi extends Employee {

    private Vehicle vehicle;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String driverServerUrl = "http://localhost:8080/driver";


    public Driver createDriver(Driver driver) {

        empId = driver.getEmpId();
        name = driver.getName();
        surname = driver.getSurname();
        phoneNumber = driver.getPhoneNumber();
        email = driver.getEmail();
        pizzeria = driver.getPizzeria();
        vehicle = driver.getVehicle();

        HttpEntity<Driver> request = new HttpEntity<>(new Driver(empId, name, surname, phoneNumber, email, pizzeria, vehicle));
        Driver createdDriver = restTemplate.postForObject(driverServerUrl + "/create", request, Driver.class);
        System.out.println(driver.toString());
        return createdDriver;
    }

    public Driver readDriver(Integer employeeId) {
        Driver readDriver = restTemplate.getForObject(driverServerUrl + "/read/" + employeeId, Driver.class);
        System.out.println(readDriver.toString());
        return readDriver;
    }

    public String updateDriver(Driver driver) {
        empId = driver.getEmpId();
        name = driver.getName();
        surname = driver.getSurname();
        phoneNumber = driver.getPhoneNumber();
        email = driver.getEmail();
        pizzeria = driver.getPizzeria();
        vehicle = driver.getVehicle();

        HttpEntity<Driver> request = new HttpEntity<>(new Driver(empId, name, surname, phoneNumber, email, pizzeria, vehicle));
        Driver updatedDriver = restTemplate.postForObject(driverServerUrl + "/update", request, Driver.class);
        System.out.println(driver.toString());

        String update = "Updated Driver: " + updatedDriver;
        return update;
    }

    public void deleteDriver(Integer employeeId) {
        String deletedDriver = driverServerUrl + "/delete/" + employeeId;
        System.out.println(deletedDriver);
        restTemplate.delete(deletedDriver);
    }




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
}
