package com.example.application.api;

import com.example.application.domain.*;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class EmployeeApi {

    protected Integer empId;

    protected String name;
    protected String surname;
    protected String phoneNumber;
    protected String email;

    protected Pizzeria pizzeria;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String employeeServerUrl = "http://localhost:8080/employee";

    public Employee createEmployee(Employee employee) {
        empId = employee.getEmpId();
        name = employee.getName();
        surname = employee.getSurname();
        phoneNumber = employee.getPhoneNumber();
        email = employee.getEmail();
        pizzeria = employee.getPizzeria();

        HttpEntity<Employee> request = new HttpEntity<>(new Employee(empId, name, surname, phoneNumber, email, pizzeria));
        Employee createdEmployee = restTemplate.postForObject(employeeServerUrl + "/create", request, Employee.class);
        System.out.println(employee.toString());
        return createdEmployee;
    }

    public Employee readEmployee(Integer employeeId) {
        Employee readEmployee = restTemplate.getForObject(employeeServerUrl + "/read/" + employeeId, Employee.class);
        System.out.println(readEmployee.toString());
        return readEmployee;
    }

    public String updateEmployee(Employee employee) {
        empId = employee.getEmpId();
        name = employee.getName();
        surname = employee.getSurname();
        phoneNumber = employee.getPhoneNumber();
        email = employee.getEmail();

        HttpEntity<Employee> request = new HttpEntity<>(new Employee(empId, name, surname, phoneNumber, email, pizzeria));
        Employee updatedEmployee = restTemplate.postForObject(employeeServerUrl + "/update", request, Employee.class);
        System.out.println(employee.toString());

        String update = "Updated Employee: " + updatedEmployee;
        return update;
    }

    public void deleteEmployee(Integer employeeId) {
        String deletedEmployee = employeeServerUrl + "/delete/" + employeeId;
        System.out.println(deletedEmployee);
        restTemplate.delete(deletedEmployee);
    }

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
