package com.example.application;


import com.example.application.api.PizzeriaApi;
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
            PizzeriaApi o = new PizzeriaApi();
            o.readPizzeria("2487d4fb-9ab9-4bf3-b975-de9838ca3be8");
    }

}
