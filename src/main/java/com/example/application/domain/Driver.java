package com.example.application.domain;
/*
Driver.java
Author: Azhar Allie Mohammed (217250513)
Date: 04/04/2023
*/

import java.util.Objects;

public class Driver extends Employee{

    private Vehicle vehicle;

    protected Driver() {
    }

    private Driver(Builder builder){
        super(builder);
        this.vehicle = builder.vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public static class Builder extends Employee.Builder {
        private Vehicle vehicle;

        public Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder copy(Driver d) {
            super.copy(d);
            this.vehicle = d.vehicle;
            return this;
        }

        public Driver build() {
            return new Driver(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(vehicle, driver.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vehicle);
    }

    @Override
    public String toString() {
        return "Driver{" +
                ", empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", pizzeria=" + pizzeria +
                ", vehicleId=" + vehicle +
                '}';
    }
}
