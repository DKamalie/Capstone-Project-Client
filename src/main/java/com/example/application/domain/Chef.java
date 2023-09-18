package com.example.application.domain;

import java.util.Objects;


/*
 * Chef.java
 * Chef Entity
 * Author: Dawood Kamalie  (220147760)
 * Date: 7/4/2023
 * */

public class Chef extends Employee {
    private String title;
    private String culinaryExperience;
    protected Chef() {

    }
    private Chef(Builder builder){
        super(builder);
       this.title = builder.title;
        this.culinaryExperience = builder.culinaryExperience;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCulinaryExperience() {
        return culinaryExperience;
    }
    public void setCulinaryExperience(String culinaryExperience) {
        this.culinaryExperience = culinaryExperience;
    }

    public static class Builder extends Employee.Builder {

        private String title;
        private String culinaryExperience;


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCulinaryExperience(String culinaryExperience) {
            this.culinaryExperience = culinaryExperience;
            return this;
        }

        public Builder copy(Chef chef) {
            super.copy(chef);
            this.title = chef.title;
            this.culinaryExperience = chef.culinaryExperience;
            return this;
        }

        public Chef build() {
            return new Chef(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Chef chef = (Chef) o;
        return Objects.equals(title, chef.title) && Objects.equals(culinaryExperience, chef.culinaryExperience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, culinaryExperience);
    }

    @Override
    public String toString() {
        return "Chef{" +
                ", empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", pizzeria=" + pizzeria +
                "title='" + title + '\'' +
                ", culinaryExperience='" + culinaryExperience + '\'' +
                '}';
    }
}
