package com.example.application.domain;

import java.util.Objects;

/* Base.java
 Entity for the Base
 Author: Timothy Lombard (220154856)
 Date: 30th July (last updated) 2023
*/

public class Base {

    public enum BaseCrust{
        CRUSTY, NON_CRUSTY
    }

    public enum BaseThickness{
        THIN, THICK
    }
    public enum BaseTexture{
        LIGHT, CHEWY, DOUGHY, CRISPY, STRETCHY
    }



    private Integer baseId;
    private BaseCrust crust;
    private BaseThickness thickness;
    private BaseTexture texture;
    private double price;

    protected Base(){

    }

    public Base(Integer baseId, BaseCrust crust, BaseThickness thickness, BaseTexture texture, double price) {
        this.baseId = baseId;
        this.crust = crust;
        this.thickness = thickness;
        this.texture = texture;
        this.price = price;
    }

    private Base(Builder builder){
        this.baseId = builder.baseId;
        this.crust = builder.crust;
        this.thickness = builder.thickness;
        this.texture = builder.texture;
        this.price = builder.price;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public BaseCrust getCrust() {
        return crust;
    }

    public BaseThickness getThickness() {
        return thickness;
    }

    public BaseTexture getTexture() {
        return texture;
    }

    public double getPrice() {
        return price;
    }

    public static class Builder {
        private Integer baseId;
        private BaseCrust crust;
        private BaseThickness thickness;
        private BaseTexture texture;
        private double price;


        public Builder setBaseId(Integer baseId) {
            this.baseId = baseId;
            return this;
        }


        public Builder setCrust(BaseCrust crust){
            this.crust = crust;
            return this;
        }

        public Builder setThickness(BaseThickness thickness){
            this.thickness = thickness;
            return this;
        }

        public Builder setTexture(BaseTexture texture){
            this.texture = texture;
            return this;
        }

        public Builder setPrice(double price){
            this.price = price;
            return this;
        }


        public Builder copy(Base base) {
            this.baseId = base.baseId;
            this.crust = base.crust;
            this.thickness = base.thickness;
            this.texture = base.texture;
            this.price = base.price;
            return this;
        }


        public Base build() {
            return new Base(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Double.compare(base.price, price) == 0 && Objects.equals(baseId, base.baseId) && crust == base.crust && thickness == base.thickness && texture == base.texture;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseId, crust, thickness, texture, price);
    }

    @Override
    public String toString() {
        return "Base{" +
                "baseId='" + baseId + '\'' +
                ", crust=" + crust +
                ", thickness=" + thickness +
                ", texture=" + texture +
                ", price= R" + price +
                '}';
    }
}
