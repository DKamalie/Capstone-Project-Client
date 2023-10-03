package com.example.application.factory;

import com.example.application.domain.Base;
import com.example.application.util.Helper;

/* BaseFactory.java
 Author: Timothy Lombard (220154856)
 Date: 30th July (last updated) 2023
*/
public class BaseFactory {

    public static Base buildBase(Base.BaseCrust crust, Base.BaseThickness thickness, Base.BaseTexture texture, double price){
        if(Helper.isNullOrEmpty(String.valueOf(crust)) || Helper.isNullOrEmpty(String.valueOf(thickness)) || Helper.isNullOrEmpty(String.valueOf(texture)) || Helper.isNullOrEmpty(String.valueOf(price))){
            return null;
        }

        Integer baseId = Helper.generateId2();

        Base b = new Base.Builder().setBaseId(baseId).setCrust(crust).setThickness(thickness).setTexture(texture).setPrice(price).build();
        return b;
    }
    public static Base createBase(Integer baseId, Base.BaseCrust crust,  Base.BaseThickness thickness, Base.BaseTexture texture, double price){
        if(Helper.isNullOrEmpty(String.valueOf(crust)) || Helper.isNullOrEmpty(String.valueOf(thickness)) || Helper.isNullOrEmpty(String.valueOf(texture)) || Helper.isNullOrEmpty(String.valueOf(price))){
           return null;
        }


        Base b = new Base.Builder().setBaseId(baseId).setCrust(crust).setThickness(thickness).setTexture(texture).setPrice(price).build();
        return b;
    }
}
