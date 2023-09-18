package com.example.application.domain;

import java.util.Objects;

/* PizzaTopping.java
 Entity for the PizzaTopping
 Author: Timothy Lombard (220154856)
 Date: 21st July (last updated) 2023
*/

public class PizzaTopping {

    private Pizza pizzaId;

    private Topping toppingId;

    protected PizzaTopping(){

    }

    private PizzaTopping(Builder builder){
        this.pizzaId = builder.pizzaId;
        this.toppingId = builder.toppingId;
    }

    public Pizza getPizza() {
        return pizzaId;
    }

    public Topping getTopping() {
        return toppingId;
    }

    public static class Builder{
        private Pizza pizzaId;
        private Topping toppingId;

        public Builder setPizza(Pizza pizzaId) {
            this.pizzaId = pizzaId;
            return this;
        }

        public Builder setTopping(Topping toppingId) {
            this.toppingId = toppingId;
            return this;
        }

        public Builder copy(PizzaTopping pt){
            this.pizzaId = pt.pizzaId;
            this.toppingId = pt.toppingId;
            return this;
        }

        public PizzaTopping build(){
            return new PizzaTopping(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaTopping that = (PizzaTopping) o;
        return Objects.equals(pizzaId, that.pizzaId) && Objects.equals(toppingId, that.toppingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, toppingId);
    }

    @Override
    public String toString() {
        return "PizzaTopping{" +
                "pizzaId=" + pizzaId +
                ", toppingId=" + toppingId +
                '}';
    }
}
