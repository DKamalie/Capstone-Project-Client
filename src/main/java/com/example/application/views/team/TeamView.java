package com.example.application.views.team;

import com.example.application.api.EmployeeApi;
import com.example.application.domain.Chef;
import com.example.application.domain.Driver;
import com.example.application.domain.Employee;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Set;

@PageTitle("Team")
@Route(value = "team", layout = MainLayout.class)
public class TeamView extends VerticalLayout {

    private Html team;
    private Html message;
    private Button teamButton;
    private Div employeeContainer;

    EmployeeApi getAll = new EmployeeApi();

    public TeamView() {
        team = new Html("<div>OUR TEAM</div>");

        message = new Html("<div>At Hill Crest, we take immense pride in our team of dedicated professionals. Our highly qualified" +
                "and experienced team members are committed to deliver the best service possible to satisfy your cravings and ensure a wonderful pizza experience.</div>");

        teamButton = new Button("View our team");

        employeeContainer = new Div();

        teamButton.addClickListener(event -> {
            try {
                Set<Employee> employees = getAll.getAllEmployee();

                Set<Chef> chefs = getAll.getAllChef();

                Set<Driver> drivers = getAll.getAllDriver();

                employeeContainer.removeAll();


                employees.forEach(employee -> {
                    employeeContainer.add(createEmployeeSpan(employee));
                });

                chefs.forEach(chef -> {
                    employeeContainer.add(createChefSpan(chef));
                });

                drivers.forEach(driver -> {
                    employeeContainer.add(createDriverSpan(driver));
                });
            } catch (Exception e) {

                System.out.println("Failed to retrieve employees. Please try again later." + e.getMessage());
            }
        });

        Style teamStyle = team.getStyle();
        teamStyle.set("font-family", "Arial");
        teamStyle.set("font-size", "30px");
        teamStyle.set("font-weight", "bold");

        Style messageStyle = message.getStyle();
        messageStyle.set("font-family", "Arial");
        messageStyle.set("font-size", "15px");

        Style teamButtonStyle = teamButton.getStyle();
        teamButtonStyle.set("color", "white");
        teamButtonStyle.set("background-color", "#000000");
        teamButtonStyle.set("font-family", "Arial");
        teamButtonStyle.set("font-size", "15px");
        teamButtonStyle.set("font-weight", "bold");


        setMargin(true);
        add(team);
        add(message);
        add(teamButton);
        add(employeeContainer);
    }


    private Span createEmployeeSpan(Employee employee) {
        return new Span(
                new Html("<table>" +
                        "<tr><td>Employee ID:</td><td>" + employee.getEmpId() + "</td></tr>" +
                        "<tr><td>Name:</td><td>" + employee.getName() + "</td></tr>" +
                        "<tr><td>Surname:</td><td>" + employee.getSurname() + "</td></tr>" +
                        "<tr><td>Phone Number:</td><td>" + employee.getPhoneNumber() + "</td></tr>" +
                        "<tr><td>Email:</td><td>" + employee.getEmail() + "</td></tr>" +
                        "<tr><td>Pizzeria:</td><td>" + employee.getPizzeria() + "</td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "</table>")
        );

    }

    private Span createChefSpan(Chef chef) {
        return new Span(
                new Html("<table>" +
                        "<tr><td>Employee ID:</td><td>" + chef.getEmpId() + "</td></tr>" +
                        "<tr><td>Name:</td><td>" + chef.getName() + "</td></tr>" +
                        "<tr><td>Surname:</td><td>" + chef.getSurname() + "</td></tr>" +
                        "<tr><td>Phone Number:</td><td>" + chef.getPhoneNumber() + "</td></tr>" +
                        "<tr><td>Email:</td><td>" + chef.getEmail() + "</td></tr>" +
                        "<tr><td>Pizzeria:</td><td>" + chef.getPizzeria() + "</td></tr>" +
                        "<tr><td>Title:</td><td>" + chef.getTitle() + "</td></tr>" +
                        "<tr><td>Culinary Experience:</td><td>" + chef.getCulinaryExperience() + "</td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "</table>")
        );
    }

    private Span createDriverSpan(Driver driver) {
        return new Span(
                new Html("<table>" +
                        "<tr><td>Employee ID:</td><td>" + driver.getEmpId() + "</td></tr>" +
                        "<tr><td>Name:</td><td>" + driver.getName() + "</td></tr>" +
                        "<tr><td>Surname:</td><td>" + driver.getSurname() + "</td></tr>" +
                        "<tr><td>Phone Number:</td><td>" + driver.getPhoneNumber() + "</td></tr>" +
                        "<tr><td>Email:</td><td>" + driver.getEmail() + "</td></tr>" +
                        "<tr><td>Pizzeria:</td><td>" + driver.getPizzeria() + "</td></tr>" +
                        "<tr><td>Vehicle:</td><td>" + driver.getVehicle() + "</td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "<tr><td></td><td></td></tr>" +
                        "</table>")

        );
    }

}