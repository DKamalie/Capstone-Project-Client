package com.example.application;

import com.example.application.api.CustomerApi;
import com.example.application.api.PizzaApi;
import com.example.application.api.PizzeriaApi;
import com.example.application.domain.*;
import com.example.application.factory.*;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
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

//        System.out.println(pizzeria);
//        PizzeriaApi o = new PizzeriaApi();
//        o.updatePizzeria(pizzeriaWID);
//        System.out.println(o.toString());

//        System.out.println(customer);
//        CustomerApi a = new CustomerApi();
//        a.updateCustomer(customer);
//        System.out.println(a.toString());

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

        PizzaApi l = new PizzaApi();
        l.updatePizza(pizza2);
        System.out.println(l.toString());
    }



}
