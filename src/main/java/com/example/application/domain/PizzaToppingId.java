package com.example.application.domain;

import java.io.Serializable;
import java.util.Objects;
/* PizzaToppingId.java
 Author: Timothy Lombard (220154856)
 Date: 21st July (last updated) 2023
*/
public class PizzaToppingId implements Serializable {

    private Pizza pizzaId;

    private Topping toppingId;

    protected PizzaToppingId(){

    }

    public PizzaToppingId(Pizza pizzaId, Topping toppingId) {
        this.pizzaId = pizzaId;
        this.toppingId = toppingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaToppingId that = (PizzaToppingId) o;
        return Objects.equals(pizzaId, that.pizzaId) && Objects.equals(toppingId, that.toppingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, toppingId);
    }
}
