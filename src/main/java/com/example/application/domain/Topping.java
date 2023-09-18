package com.example.application.domain;

import java.util.Objects;
/* Topping.java
 Entity for the Topping
 Author: Timothy Lombard (220154856)
 Date: 30th July (last updated) 2023
*/

public class Topping {

    private String toppingId;
    private String name;
    private String description;
    private int quantity;
    private double price;

    protected Topping(){

    }

    private Topping(Builder builder){
        this.toppingId = builder.toppingId;
        this.name = builder.name;
        this.description = builder.description;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public String getToppingId() {
        return toppingId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public static class Builder {
        private String toppingId;
        private String name;
        private String description;
        private int quantity;
        private double price;


        public Builder setToppingId(String toppingId) {
            this.toppingId = toppingId;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Builder setQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(double price){
            this.price = price;
            return this;
        }

        public Builder copy(Topping topping) {
            this.toppingId = topping.toppingId;
            this.name = topping.name;
            this.description = topping.description;
            this.quantity = topping.quantity;
            this.price = topping.price;
            return this;
        }


        public Topping build() {
            return new Topping(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topping topping = (Topping) o;
        return quantity == topping.quantity && Double.compare(topping.price, price) == 0 && Objects.equals(toppingId, topping.toppingId) && Objects.equals(name, topping.name) && Objects.equals(description, topping.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toppingId, name, description, quantity, price);
    }

    @Override
    public String toString() {
        return "Topping{" +
                "Topping Id='" + toppingId + '\'' +
                ", Topping name='" + name + '\'' +
                ", Topping description='" + description + '\'' +
                ", Topping quantity=" + quantity +
                ", Topping price= R" + price +
                '}';
    }
}
