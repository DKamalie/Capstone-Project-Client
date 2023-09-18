package com.example.application.views.home;

import com.example.application.api.PizzeriaApi;
import com.example.application.domain.Pizzeria;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    private TextField temp;

    public HomeView() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        temp = new TextField("");
        sayHello.addClickListener(e -> {
            Pizzeria r = new Pizzeria();
                    PizzeriaApi o = new PizzeriaApi();
                    r = o.readPizzeria("2487d4fb-9ab9-4bf3-b975-de9838ca3be8");
                    name.setValue(r.getLocation());
            Notification.show("This Prints " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(name, sayHello);
    }

}
