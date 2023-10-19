package com.example.application.factory;

import com.example.application.domain.Employee;
import com.example.application.domain.Pizzeria;
import com.example.application.util.Helper;

/*
* EmployeeFactory.java
* Author: Dawood Kamalie  (220147760)
* Date: 7/4/2023
* */

public class EmployeeFactory {
    public static Employee buildEmployee(String name, String surname, String phoneNumber, String email, Pizzeria pizzeria) {
        if (Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(surname) || Helper.isNullOrEmpty(phoneNumber) || Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(String.valueOf(pizzeria))) {
            return null;
        }

        int empId = Helper.generateId2();

       Employee employee = new Employee.Builder().setEmpId(empId).setName(name).setSurname(surname).setPhoneNumber(phoneNumber).setEmail(email).setPizzeria(pizzeria).build();
       return employee;
    }

    public static Employee createEmployee(Integer empId, String name, String surname, String phoneNumber, String email, Pizzeria pizzeria) {
        if (Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(surname) || Helper.isNullOrEmpty(phoneNumber) || Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(String.valueOf(pizzeria))) {
            return null;
        }


        Employee employee = new Employee.Builder().setEmpId(empId).setName(name).setSurname(surname).setPhoneNumber(phoneNumber).setEmail(email).setPizzeria(pizzeria).build();
        return employee;
    }


}
