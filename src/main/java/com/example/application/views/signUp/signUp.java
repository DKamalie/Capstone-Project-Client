package com.example.application.views.signUp;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.InputMismatchException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.dom.Style;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.InputMismatchException;

/*
 Sign Up View.java
 Author: Keenan Meyer (220194920)
 Date: 9th September (last updated) 2023
*/
@Route("/signup")
public class signUp extends VerticalLayout{
        private Text alreadyWithUs;
        //    private Text checkIfYoureaLoyaltyCustomer;
        private RouterLink loginLink;
        private TextField firstName;
        private TextField lastName;
        private TextField phoneNumber;
        private TextField username;
        private PasswordField password;
        private Button btnSubmit;
        private HorizontalLayout hL;
        private VerticalLayout mainframe;


        public signUp() {

            alreadyWithUs = new Text("Already with us?");
            //loginLink = new RouterLink("Login", LoginView.class);
            hL = new HorizontalLayout();
            mainframe = new VerticalLayout();
            hL.add(alreadyWithUs, loginLink);
            firstName = new TextField("First name:");
            firstName.setWidth("300px");
            firstName.setPlaceholder("Enter your first name");
            lastName = new TextField("Last name:");
            lastName.setWidth("300px");
            lastName.setPlaceholder("Enter your last name");
            phoneNumber = new TextField("Phone Number:");
            phoneNumber.setWidth("300px");
            phoneNumber.setPlaceholder("Enter your Phone Number");
            username = new TextField("Username:");
            username.setWidth("300px");
            username.setPlaceholder("Enter your username");
            password = new PasswordField("Password:");
            password.setWidth("300px");
            password.setPlaceholder("Enter your password");
            btnSubmit = new Button("Submit");
            btnSubmit.setWidth("300px");


            btnSubmit.addClickListener(e -> {
                try {

                   // getUI().ifPresent(ui -> ui.navigate(HomeView.class));

                } catch (Exception exception) {
                    Notification.show(exception.getMessage());
                }
            });

            Style buttonStyle = btnSubmit.getStyle();
            buttonStyle.set("color", "white");
            buttonStyle.set("background-color", "#8F6666");
            buttonStyle.set("font-family", "Arial");
            buttonStyle.set("font-size", "16px");
            buttonStyle.set("font-weight", "bold");
            buttonStyle.set("border-radius", "17px");
            buttonStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
            buttonStyle.set("margin-right", "auto");
            buttonStyle.set("margin-left", "auto");

            Style alreadyStyle = hL.getStyle();
            alreadyStyle.set("font-family", "Arial");
            alreadyStyle.set("font-size", "16px");
            alreadyStyle.set("font-weight", "bold");
            alreadyStyle.set("margin-right", "auto");
            alreadyStyle.set("margin-left", "auto");

            Style firstNameTextField = firstName.getStyle();
            firstNameTextField.set("font-family", "Arial");
            firstNameTextField.set("font-size", "15px");
            firstNameTextField.set("margin-right", "auto");
            firstNameTextField.set("margin-left", "auto");

            Style lastNameTextField = lastName.getStyle();
            lastNameTextField.set("font-family", "Arial");
            firstNameTextField.set("font-size", "15px");
            lastNameTextField.set("margin-right", "auto");
            lastNameTextField.set("margin-left", "auto");

            Style emailTextField = phoneNumber.getStyle();
            emailTextField.set("font-family", "Arial");
            emailTextField.set("font-size", "15px");
            emailTextField.set("margin-right", "auto");
            emailTextField.set("margin-left", "auto");

            Style usernameTextField = username.getStyle();
            usernameTextField.set("font-family", "Arial");
            usernameTextField.set("font-size", "15px");
            usernameTextField.set("margin-right", "auto");
            usernameTextField.set("margin-left", "auto");

            Style passwordTextField = password.getStyle();
            passwordTextField.set("font-family", "Arial");
            passwordTextField.set("font-size", "15px");
            passwordTextField.set("margin-right", "auto");
            passwordTextField.set("margin-left", "auto");

            setMargin(true);

            mainframe.add(hL);
            mainframe.add(firstName);
            mainframe.add(lastName);
            mainframe.add(phoneNumber);
            mainframe.add(username);
            mainframe.add(password);
            mainframe.add(btnSubmit);
            add(mainframe);

        }
        public Boolean Errors() {

            String firstNameValue = firstName.getValue();
            String lastNameValue = lastName.getValue();
            String phoneValue = phoneNumber.getValue();
            String usernameValue = username.getValue();
            String passwordValue = password.getValue();

            if (firstNameValue.isEmpty() || lastNameValue.isEmpty() || phoneValue.isEmpty() || usernameValue.isEmpty() || passwordValue.isEmpty()) {
                Notification.show("Please enter in all the required fields");
                return true;
            }

            if(!firstNameValue.matches("[a-zA-Z]+") || !lastNameValue.matches("[a-zA-Z]+") ){
                throw new InputMismatchException(("Invalid input, please only enter in letters for your first name or last name"));
            }

            if (phoneValue.length() < 10){
                Notification.show("Invalid Phone Number, please try again");
                return true;
            }
            if(passwordValue.length() < 8){
                Notification.show("The password length is below 8");
                return true;
            }
            return false;
        }
}
