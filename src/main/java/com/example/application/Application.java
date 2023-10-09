package com.example.application;

import com.example.application.api.VehicleApi;
import com.example.application.domain.*;
import com.example.application.factory.*;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@SpringBootApplication
@NpmPackage(value = "@fontsource/lato", version = "4.5.0")
@Theme(value = "capstoneclient")
public class Application implements AppShellConfigurator {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


        Pizzeria pizzeria = PizzeriaFactory.buildPizzaria(
            "Home work",
            "Please work");

        Pizzeria pizzeriaWID = PizzeriaFactory.buildPizzaria(
                -1035790709,
                "Home NO WORK",
                "Please work");


          Address address = AddressFactory.buildAddress(
        "21",
        "Jump Street",
        "West Olmstead",
        "Bikini Bottom",
        "California",
        "Crownland",
        "0007",
        AddressType.RESIDENTIAL_HOME);

        Customer customer = CustomerFactory.buildCustomer(
                1,
                "Jollie,loly",
                "Working",
                "0666689777",
                address);

        LocalDate date = LocalDate.now();
         LoyaltyCustomer lc1 = LoyaltyCustomerFactory.createLoyaltyCustomer(
                "Keenan",
                "Meyer",
                "0852849389",
                address,
                date,
                10.34,
                "12345678",
                "luto@gmail.com");

         Base base = BaseFactory.buildBase( Base.BaseCrust.CRUSTY,
                 Base.BaseThickness.THIN, Base.BaseTexture.CRISPY,
                 20);
         Pizza pizza = PizzaFactory.createPizza(
                 -2049057700,
                 base,
                 "Margherita pizza",
                 "none",
                 Pizza.Size.SMALL,
                 false,
                 55,
                 pizzeria);

        Pizza pizza2 = PizzaFactory.createPizza(
                base,
                "Margherita pizza",
                "Thin crust with high quality flour and fresh tomato sauce and with creamy extra cheese.",
                Pizza.Size.SMALL,
                false,
                55,
                pizzeria);


        Vehicle getVehicleData = VehicleFactory.buildVehicle(466555689, "nn", "nn", "nn", "2015", "bue");
        VehicleApi vehicleApi = new VehicleApi();
        System.out.println(getVehicleData);
        vehicleApi.deleteVehicle(1843803432);



    }

  //  Vehicle getVehicleData = VehicleFactory.buildVehicle(vehicleIdValue, vehicleTypeValue, makeValue, modelValue, yearValue, colourValue);



}
