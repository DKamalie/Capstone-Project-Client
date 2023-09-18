package com.example.application.domain;

import java.util.Objects;

/*
 * Employee.Java
 * Employee Entity
 * Author: Dawood Kamalie (220147760)
 * Date: 5 April 2023
 * */

public class Employee {

    protected String empId;

    protected String name;
    protected String surname;
    protected String phoneNumber;
    protected String email;

    protected Pizzeria pizzeria;

    protected Employee() {

    }


    protected Employee(Builder builder) {
        this.empId = builder.empId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.pizzeria = builder.pizzeria;
    }
    public String getEmpId() {
        return empId;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    public static class Builder {
        private String empId;
        private String name;
        private String surname;
        private String phoneNumber;
        private String email;
        protected Pizzeria pizzeria;

        public Builder setEmpId(String empId) {
            this.empId = empId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPizzeria(Pizzeria pizzeria) {
            this.pizzeria = pizzeria;
            return this;
        }

        public Builder copy(Employee employee){
            this.empId = employee.empId;
            this.name = employee.name;
            this.surname = employee.surname;
            this.phoneNumber = employee.phoneNumber;
            this.email = employee.email;
            this.pizzeria = employee.pizzeria;
            return this;
        }



        public Employee build(){
            return new Employee(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(email, employee.email) && Objects.equals(pizzeria, employee.pizzeria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, name, surname, phoneNumber, email, pizzeria);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", pizzeria=" + pizzeria +
                '}';
    }
}