package com.example.application;

import com.example.application.api.PizzeriaApi;
import com.example.application.domain.Pizzeria;
import com.example.application.factory.PizzeriaFactory;
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
            "Hill Crest Work",
            "Hotel Transylvania");



//        Address n = new Address();
//        n.setStreetNumber("21");
//        n.setStreetName("Jump Street");
//        n.setSuburb("West Olmstead");
//        n.setCity("Bikini Bottom");
//        n.setCity("California");
//        n.setProvince("Crownland");
//        n.setPostalCode("0007");
//        n.setAddressType(AddressType.RESIDENTIAL_HOME);
//
//        Customer b = new Customer();
//        b.setCustomerName("Homie");
//        b.setCustomerSurname("Kahn");
//        b.setPhoneNumber("0677717742");
//        b.setAddress(n);

//        System.out.println(pizzeria);
//        PizzeriaApi o = new PizzeriaApi();
//        o.createPizzeria(pizzeria);
//        System.out.println(o.toString());


//        CustomerApi a = new CustomerApi();
//        a.createCustomer(b);


    }

}
