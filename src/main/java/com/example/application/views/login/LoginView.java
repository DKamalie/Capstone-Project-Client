package com.example.application.views.login;

import com.example.application.api.LoyaltyCustomerApi;
import com.example.application.domain.LoyaltyCustomer;
import com.example.application.views.MainLayout;
import com.example.application.views.admindashboard.AdminDashboard;
import com.example.application.views.home.HomeView;
import com.example.application.views.signUp.SignUpView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;

/*
Login View.java
Author: Azhar Allie Mohammed (217250513)
Date: 30/09/2023
*/
@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
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


        btnLogin.addClickListener(e -> {handleLogin();});

        Text notMember = new Text("Not a Member?");
        RouterLink signUpLink = new RouterLink("SignUp", SignUpView.class);

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

        hl.add(notMember, signUpLink);
        hl2.add(btnLogin, btnCancel);
        add(spacer, email, password, hl2, hl);
    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (VaadinSession.getCurrent().getAttribute("loggedInCustomer") != null) {
            event.forwardTo(HomeView.class);
        }
    }

    private void handleLogin() {
        String enteredEmail = email.getValue();
        String enteredPassword = password.getValue();

        // Check if the entered credentials match the admin user
        if ("admin@gmail.com".equals(enteredEmail) && "admin".equals(enteredPassword)) {
            // Admin login, set the matchedCustomer object in the session
            LoyaltyCustomer adminCustomer = loyaltyCustomerApi.getLoggedInCustomer(enteredEmail, enteredPassword);
            VaadinSession.getCurrent().setAttribute("loggedInCustomer", adminCustomer);
            // Fire the login success event
            EventBus.getInstance().fireLoginSuccessEvent(new LoginSuccessEvent(adminCustomer));
            // Navigate to AdminDashboard
            UI.getCurrent().navigate(AdminDashboard.class);
        } else {
            // For regular customers, check against the loyaltyCustomerApi
            LoyaltyCustomer matchedCustomer = loyaltyCustomerApi.getLoggedInCustomer(enteredEmail, enteredPassword);

            if (matchedCustomer != null) {
                // Regular customer login, set the matchedCustomer object in the session
                VaadinSession.getCurrent().setAttribute("loggedInCustomer", matchedCustomer);
                // Fire the login success event
                EventBus.getInstance().fireLoginSuccessEvent(new LoginSuccessEvent(matchedCustomer));
                // Navigate to HomeView
                UI.getCurrent().navigate(HomeView.class);
            } else {
                Notification.show("Invalid email or password. Please try again.");
            }
        }
    }


    public boolean isValidEmail (String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return email.matches(emailRegex);
    }

}
