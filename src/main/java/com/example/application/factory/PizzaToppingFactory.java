package com.example.application.factory;


import com.example.application.domain.Pizza;
import com.example.application.domain.PizzaTopping;
import com.example.application.domain.Topping;
import com.example.application.util.Helper;

/* PizzaToppingFactory.java
 Author: Timothy Lombard (220154856)
 Date: 13th June (last updated) 2023
*/
public class PizzaToppingFactory {

    public static PizzaTopping buildPizzaTopping(Pizza pizzaId, Topping toppingId){
        if(Helper.isNullOrEmpty(String.valueOf(pizzaId)) || Helper.isNullOrEmpty(String.valueOf(toppingId))){
            return null;
        }

        PizzaTopping pt = new PizzaTopping.Builder().setPizza(pizzaId).setTopping(toppingId).build();
        return pt;
    }

}
