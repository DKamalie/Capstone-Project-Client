package com.example.application.views.checkout;

import com.example.application.domain.LoyaltyCustomer;
import com.example.application.views.MainLayout;
import com.example.application.views.home.HomeView;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Thank You")
@Route(value = "thankyou", layout = MainLayout.class)
public class ThankYouView extends VerticalLayout {

    private Image imgPizza;
    private Button btnViewMenu;
    private String customerName;

    public ThankYouView() {
        // Configure the layout
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setWidthFull();
        setHeight("100vh");

        if (isUserLoggedIn()) {
            LoyaltyCustomer loggedInCustomer = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("loggedInCustomer");
            customerName = loggedInCustomer.getCustomerName();
        }

        // Create a title
        H1 title = new H1("Thank You, "+customerName+"!");

        // Style the title
        title.getStyle().set("color", "var(--lumo-success-text-color)");
        title.getStyle().set("font-size", "3rem");

        // Create a message
        String message = "Your request has been received. We'll get back to you soon!";
        Paragraph messageText = new Paragraph(message);

        // Add components to the layout
        add(title, messageText);

    }

    private boolean isUserLoggedIn() {
        return VaadinSession.getCurrent().getAttribute("loggedInCustomer") != null;
    }

}
