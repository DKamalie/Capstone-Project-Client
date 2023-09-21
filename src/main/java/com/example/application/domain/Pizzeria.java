package com.example.application.domain;

import java.io.Serializable;
import java.util.Objects;

/* Pizzeria.java
   Entity for the Pizzeria
   Author: Keenan Meyer (220194920)
   Date: 30th March 2023
*/

public class Pizzeria implements Serializable {

    private Integer pizzeriaID;
    private String pizzariaAlias;
    private String location;

    public Pizzeria(){
    }
    public Pizzeria(Integer pizzeriaID,String pizzariaAlias,String location){
        this.pizzeriaID = pizzeriaID;
        this.pizzariaAlias = pizzariaAlias;
        this.location = location;
    }


        private Pizzeria(Builder builder){
        this.pizzeriaID = builder.pizzeriaID;
        this.pizzariaAlias = builder.pizzariaAlias;
        this.location = builder.location;
    }

    public Integer getPizzeriaID() {
        return pizzeriaID;
    }

    public String getPizzariaAlias() {
        return pizzariaAlias;
    }

    public String getLocation() {
        return location;
    }




    public static class Builder {
        private Integer pizzeriaID;
        private String pizzariaAlias;
        private String location;

        public Builder setPizzariaID(Integer pizzeriaID) {
            this.pizzeriaID = pizzeriaID;
            return this;
        }
        public Builder setPizzariaAlias(String pizzariaAlias) {
            this.pizzariaAlias = pizzariaAlias;
            return this;
        }
        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder copy(Pizzeria t) {
            this.pizzeriaID = t.pizzeriaID;
            this.pizzariaAlias = t.pizzariaAlias;
            this.location = t.location;
            return this;
        }

        public Pizzeria build(){
            return new Pizzeria(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizzeria pizzeria = (Pizzeria) o;
        return Objects.equals(pizzeriaID, pizzeria.pizzeriaID) && Objects.equals(pizzariaAlias, pizzeria.pizzariaAlias) && Objects.equals(location, pizzeria.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzeriaID, pizzariaAlias, location);
    }

    @Override
    public String toString() {
        return "Pizzeria{" +
                "pizzeriaID='" + pizzeriaID + '\'' +
                ", pizzariaAlias='" + pizzariaAlias + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
