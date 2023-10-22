package com.example.application.views.admindashboard;

import com.example.application.views.MainLayout;
import com.example.application.views.admin.base.BaseView;
import com.example.application.views.admin.employee.ChefView;
import com.example.application.views.admin.employee.DriverView;
import com.example.application.views.admin.employee.EmployeeView;
import com.example.application.views.admin.pizza.PizzaAdminView;
import com.example.application.views.admin.topping.ToppingAdminView;
import com.example.application.views.admin.vehicle.VehicleView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@PageTitle("Admin")
@Route(value = "admin", layout = MainLayout.class)
public class AdminDashboard extends VerticalLayout {

    public AdminDashboard() {
        // Header
        H2 header = new H2("Admin Dashboard");
        add(header);

        // List of items
        add(createItemLink("Our Vehicles", VehicleView.class));
        add(createItemLink("Toppings", ToppingAdminView.class));
        add(createItemLink("Chefs", ChefView.class));
        add(createItemLink("Drivers", DriverView.class));
        add(createItemLink("All Employees", EmployeeView.class));
        add(createItemLink("Pizza base", BaseView.class));
        add(createItemLink("Pizza", PizzaAdminView.class));
    }

    private Component createItemLink(String itemName, Class<?> navigationTarget) {
        // Router link with bullet point and item name
        RouterLink link = new RouterLink(itemName, (Class<? extends Component>) navigationTarget);
        link.getStyle()
                .set("text-decoration", "none") // Optional: remove underline from the link
                .set("color", "black"); // Optional: set link color

        // Bullet point
        Div bulletPoint = new Div();
        bulletPoint.getStyle()
                .set("margin-right", "8px")
                .set("width", "10px")
                .set("height", "10px")
                .set("border-radius", "50%")
                .set("background-color", "black");

        // Container for bullet point and item name
        Div container = new Div(bulletPoint, link);
        container.getStyle().set("display", "flex").set("align-items", "center");

        return container;
    }
}
