package com.example.application.factory;

import com.example.application.domain.Chef;
import com.example.application.domain.Pizzeria;
import com.example.application.util.Helper;

/*
* ChefFactory.java
* Author: Dawood Kamalie  (220147760)
* Date: 7/4/2023
* */
public class ChefFactory {
    public static Chef buildChef(String name, String surname, String phoneNumber, String email, String title, String culinaryExperience, Pizzeria pizzeria) {
        if (  Helper.isNullOrEmpty(title) || Helper.isNullOrEmpty(culinaryExperience)) {
            return null;
        }

        Integer empId = Helper.generateId2();

        Chef chef = (Chef) new Chef.Builder().setTitle(title).setCulinaryExperience(culinaryExperience).setEmpId(empId).setName(name).setSurname(surname).setPhoneNumber(phoneNumber).setEmail(email).setPizzeria(pizzeria).build();
        return chef;

    }

    public static Chef createChef(Integer empId, String name, String surname, String phoneNumber, String email, String title, String culinaryExperience, Pizzeria pizzeria) {
        if (  Helper.isNullOrEmpty(title) || Helper.isNullOrEmpty(culinaryExperience)) {
            return null;
        }


        Chef chef = (Chef) new Chef.Builder().setTitle(title).setCulinaryExperience(culinaryExperience).setEmpId(empId).setName(name).setSurname(surname).setPhoneNumber(phoneNumber).setEmail(email).setPizzeria(pizzeria).build();
        return chef;

    }
}
