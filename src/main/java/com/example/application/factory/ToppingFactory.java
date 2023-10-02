package com.example.application.factory;

import com.example.application.domain.Topping;
import com.example.application.util.Helper;
/* ToppingFactory.java
 Author: Timothy Lombard (220154856)
 Date: 13th June (last updated) 2023
*/
public class ToppingFactory {

    public static Topping buildTopping(String name, String description, int quantity, double price){//generates a Id
        if(Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(String.valueOf(quantity)) || Helper.isNullOrEmpty(String.valueOf(price))){
            return null;
        }

        int toppingId = Helper.generateId2();

        Topping t = new Topping.Builder().setToppingId(toppingId).setName(name).setDescription(description).setQuantity(quantity).setPrice(price).build();
        return t;
    }

    public static Topping createTopping(Integer toppingId, String name, String description, int quantity, double price){//takes a Id
        if(Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(String.valueOf(quantity)) || Helper.isNullOrEmpty(String.valueOf(price))){
            return null;
        }

        Topping topping = new Topping.Builder().setToppingId(toppingId).setName(name).setDescription(description).setQuantity(quantity).setPrice(price).build();

        return topping;
    }
}
