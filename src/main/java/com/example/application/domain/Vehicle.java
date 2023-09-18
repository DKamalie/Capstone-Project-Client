package com.example.application.domain;

import java.util.Objects;

/*
Vehicle.java
Author: Azhar Allie Mohammed (217250513)
Date: 04/04/2023
*/

public class Vehicle {

    private String vehicleId;
    private String vehicleType;
    private String make;
    private String model;
    private String year;
    private String colour;

    protected Vehicle() {
    }

    private Vehicle(Builder builder){
        this.vehicleId = builder.vehicleId;
        this.vehicleType = builder.vehicleType;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.colour = builder.colour;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColour() {
        return colour;
    }

    public static class Builder {
        private String vehicleId;
        private String vehicleType;
        private String make;
        private String model;
        private String year;
        private String colour;

        public Builder setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public Builder setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public Builder setColour(String colour) {
            this.colour = colour;
            return this;
        }

        public Builder copy(Vehicle v) {
            this.vehicleId = v.vehicleId;
            this.vehicleType = v.vehicleType;
            this.make = v.make;
            this.model = v.model;
            this.year = v.year;
            this.colour = v.colour;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleId, vehicle.vehicleId) && Objects.equals(vehicleType, vehicle.vehicleType) && Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model) && Objects.equals(year, vehicle.year) && Objects.equals(colour, vehicle.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, vehicleType, make, model, year, colour);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
