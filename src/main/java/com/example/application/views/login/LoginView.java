package com.example.application.views.login;

import com.example.application.api.AuthenticateUserApi;
import com.example.application.api.LoyaltyCustomerApi;
import com.example.application.domain.LoyaltyCustomer;
import com.example.application.views.MainLayout;
import com.example.application.views.home.HomeView;
import com.example.application.views.signUp.Both;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

import java.util.Set;

/*
Login View.java
Author: Azhar Allie Mohammed (217250513)
Date: 30/09/2023
*/
@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
public class LoginView extends VerticalLayout {
    private TextField email;
    private PasswordField password;
    private Button btnLogin;
    private Button btnCancel;
    private HorizontalLayout hl = new HorizontalLayout();
    private HorizontalLayout hl2 = new HorizontalLayout();
    private final LoyaltyCustomerApi loyaltyCustomerApi;


    public LoginView(LoyaltyCustomerApi loyaltyCustomerApi) {
        this.loyaltyCustomerApi = loyaltyCustomerApi;

        email = new TextField("Email:");
        email.setWidth("300px");
        email.setPlaceholder("Enter your email");

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setPlaceholder("Enter your password");

        btnLogin = new Button("Login");
        btnLogin.setWidth("150px");

        btnCancel = new Button("Cancel");
        btnCancel.setWidth("150px");
        btnCancel.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(HomeView.class));
        });

        btnLogin.addClickListener(e -> {
            String enteredEmail = email.getValue();
            String enteredPassword = password.getValue();

            Set<LoyaltyCustomer> loyaltyCustomers = loyaltyCustomerApi.getAllLoyaltyCustomer();

            boolean loginSuccessful = false;

            for (LoyaltyCustomer customer : loyaltyCustomers) {
                if (customer.getEmail().equals(enteredEmail) && customer.getPassword().equals(enteredPassword)) {
                    loginSuccessful = true;
                    Notification.show("Welcome " + customer.getCustomerName() +"!");

                    AuthenticateUserApi authenticateUserApi = new AuthenticateUserApi();
                    authenticateUserApi.getToken(enteredEmail, enteredPassword);
                    break;
                }
            }

            if (loginSuccessful) {
                Notification.show("Login successful!");
                getUI().ifPresent(ui -> ui.navigate(HomeView.class));
            } else {
                Notification.show("Invalid email or password. Please try again.");
            }
        });


        Text notMember = new Text("Not a Member?");
        RouterLink signUpLink = new RouterLink("SignUp", Both.class);

        Style buttonStyle = btnLogin.getStyle();
        buttonStyle.set("color", "white");
        buttonStyle.set("background-color", "#000000");
        buttonStyle.set("font-family", "Arial");
        buttonStyle.set("font-size", "16px");
        buttonStyle.set("font-weight", "bold");
        buttonStyle.set("border-radius", "17px");
        buttonStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
        buttonStyle.set("margin-right", "auto");
        buttonStyle.set("margin-left", "auto");

        Style buttonStyle1 = btnCancel.getStyle();
        buttonStyle1.set("color", "white");
        buttonStyle1.set("background-color", "#000000");
        buttonStyle1.set("font-family", "Arial");
        buttonStyle1.set("font-size", "16px");
        buttonStyle1.set("font-weight", "bold");
        buttonStyle1.set("border-radius", "17px");
        buttonStyle1.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
        buttonStyle1.set("margin-right", "auto");
        buttonStyle1.set("margin-left", "auto");

        Style emailTextField = email.getStyle();
        emailTextField.set("font-family", "Arial");
        emailTextField.set("font-size", "15px");
        emailTextField.set("margin-right", "auto");
        emailTextField.set("margin-left", "auto");

        Style passwordTextField = password.getStyle();
        passwordTextField.set("font-family", "Arial");
        passwordTextField.set("font-size", "15px");
        passwordTextField.set("margin-right", "auto");
        passwordTextField.set("margin-left", "auto");

        Div spacer = new Div();
        spacer.setHeight("15vh");

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setMargin(true);

        hl.add(notMember,signUpLink);
        hl2.add(btnLogin,btnCancel);
        add(spacer,email, password,hl2,hl);
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return email.matches(emailRegex);
    }

}
