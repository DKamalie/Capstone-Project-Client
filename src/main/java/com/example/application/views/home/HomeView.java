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
//@RouteAlias(value = "", layout = MainLayout.class)    //made WelcomeView the "landing page"
public class HomeView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    private TextField temp;

    public HomeView() {
        name = new TextField("");
        sayHello = new Button("Say hello");
        temp = new TextField("");
        sayHello.addClickListener(e -> {

            Pizzeria object = new Pizzeria();

                    PizzeriaApi o = new PizzeriaApi();
                    object = o.readPizzeria(1);

                    name.setValue(object.getLocation());

            Notification.show("This Prints " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(name, sayHello);
    }

}
