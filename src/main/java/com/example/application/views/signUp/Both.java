package com.example.application.views.signUp;

import com.example.application.domain.Address;
import com.example.application.domain.AddressType;
import com.example.application.domain.Customer;
import com.example.application.domain.LoyaltyCustomer;
import com.example.application.factory.AddressFactory;
import com.example.application.factory.CustomerFactory;
import com.example.application.factory.LoyaltyCustomerFactory;
import com.example.application.views.MainLayout;
import com.example.application.views.home.HomeView;
import com.example.application.views.login.LoginView;
import com.example.application.views.welcome.WelcomeView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.*;
import java.time.LocalDate;
import java.util.InputMismatchException;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/*
 Sign Up  View.java
 Author: Keenan Meyer (220194920)
 Date: 4th October  2023
*/
@PageTitle("both")
@Route(value = "both", layout = MainLayout.class)
public class Both extends VerticalLayout{
    private Text alreadyWithUs;
    private RouterLink loginLink;

    //customer
    private TextField firstName;
    private TextField lastName;
    private TextField phoneNumber;
    private TextField email;
    private PasswordField password;

    //address
    private TextField streetNumber;
    private TextField streetName;
    private TextField suburb;
    private TextField city;
    private TextField province;
    private TextField country;
    private TextField postalCode;

    private Button btnSubmit;
    private Button btnNext;
    private HorizontalLayout hL;
    private VerticalLayout mainframe;


    public Both() {

        alreadyWithUs = new Text("Already with us?");
        loginLink = new RouterLink("Login", LoginView.class);
        hL = new HorizontalLayout();
        mainframe = new VerticalLayout();
        hL.add(alreadyWithUs, loginLink);

        //customer
        firstName = new TextField("First name:");
        firstName.setWidth("300px");
        firstName.setPlaceholder("Enter your first name");
        lastName = new TextField("Last name:");
        lastName.setWidth("300px");
        lastName.setPlaceholder("Enter your last name");
        phoneNumber = new TextField("Phone number:");
        phoneNumber.setWidth("300px");
        phoneNumber.setPlaceholder("Enter your phone number");
        email = new TextField("Email:");
        email.setWidth("300px");
        email.setPlaceholder("Enter your email");
        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setPlaceholder("Enter your password");


        //address
        streetNumber = new TextField("Street Number:");
        streetNumber.setWidth("300px");
        streetNumber.setPlaceholder("Enter your street number");
        streetName = new TextField("Street Name:");
        streetName.setWidth("300px");
        streetName.setPlaceholder("Enter your street name");
        suburb = new TextField("Suburb:");
        suburb.setWidth("300px");
        suburb.setPlaceholder("Enter your suburb");
        city = new TextField("City:");
        city.setWidth("300px");
        city.setPlaceholder("Enter your city");
        province = new TextField("Province:");
        province.setWidth("300px");
        province.setPlaceholder("Enter your province");
        country = new TextField("Country:");
        country.setWidth("300px");
        country.setPlaceholder("Enter your country");
        postalCode = new TextField("Postal Code:");
        postalCode.setWidth("300px");
        postalCode.setPlaceholder("Enter your postal code");
        btnSubmit = new Button("Submit");
        btnSubmit.setWidth("300px");
        btnNext = new Button("Next");
        btnNext.setWidth("300px");

        //address visiblity
        streetNumber.setVisible(false);
        streetName .setVisible(false);
        suburb.setVisible(false);
        city.setVisible(false);
        province.setVisible(false);
        country.setVisible(false);
        postalCode.setVisible(false);
        btnSubmit.setVisible(false);

        //clicking Submit button
        btnNext.addClickListener(e -> {
            try {
                if(customerErrors()  ==  false) {
                    //customer
                    firstName.setVisible(false);
                    lastName.setVisible(false);
                    phoneNumber.setVisible(false);
                    email.setVisible(false);
                    password.setVisible(false);
                    btnNext.setVisible(false);

                    //address
                    streetNumber.setVisible(true);
                    streetName.setVisible(true);
                    suburb.setVisible(true);
                    city.setVisible(true);
                    province.setVisible(true);
                    country.setVisible(true);
                    postalCode.setVisible(true);
                    btnSubmit.setVisible(true);

                } else{
                    System.out.println("entered Customer errors");
                }

            } catch (Exception exception) {
            Notification.show(exception.getMessage());
        }
        });


        //clicking Submit button
        btnSubmit.addClickListener(e -> {
            try {
                if(addressErrors()  ==  false){
                    LoyaltyCustomer  l = setValues();
                    System.out.println(l.toString());
//                  createLoyaltyCustomer(l);

                Notification.show("You are now logged in as " + l.getCustomerName());
                System.out.println(l.getCustomerName());

                getUI().ifPresent(ui -> ui.navigate(WelcomeView.class));
                }else{
                    System.out.println("entered Address errors");
                }

            } catch (Exception exception) {
                Notification.show(exception.getMessage());
            }
        });

        //customer
        Style firstNameTextField = firstName.getStyle();
        firstNameTextField.set("font-family", "Arial");
        firstNameTextField.set("font-size", "15px");
        firstNameTextField.set("margin-right", "auto");
        firstNameTextField.set("margin-left", "auto");

        Style lastNameTextField = lastName.getStyle();
        lastNameTextField.set("font-family", "Arial");
        lastNameTextField.set("font-size", "15px");
        lastNameTextField.set("margin-right", "auto");
        lastNameTextField.set("margin-left", "auto");

        Style emailTextField = phoneNumber.getStyle();
        emailTextField.set("font-family", "Arial");
        emailTextField.set("font-size", "15px");
        emailTextField.set("margin-right", "auto");
        emailTextField.set("margin-left", "auto");

        Style usernameTextField = email.getStyle();
        usernameTextField.set("font-family", "Arial");
        usernameTextField.set("font-size", "15px");
        usernameTextField.set("margin-right", "auto");
        usernameTextField.set("margin-left", "auto");

        Style passwordTextField = password.getStyle();
        passwordTextField.set("font-family", "Arial");
        passwordTextField.set("font-size", "15px");
        passwordTextField.set("margin-right", "auto");
        passwordTextField.set("margin-left", "auto");

        //address
        Style buttonStyle = btnSubmit.getStyle();
        buttonStyle.set("color", "white");
        buttonStyle.set("background-color", "#000000");
        buttonStyle.set("font-family", "Arial");
        buttonStyle.set("font-size", "16px");
        buttonStyle.set("font-weight", "bold");
        buttonStyle.set("border-radius", "17px");
        buttonStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
        buttonStyle.set("margin-right", "auto");
        buttonStyle.set("margin-left", "auto");

        Style buttonStyle2 = btnNext.getStyle();
        buttonStyle2.set("color", "white");
        buttonStyle2.set("background-color", "#000000");
        buttonStyle2.set("font-family", "Arial");
        buttonStyle2.set("font-size", "16px");
        buttonStyle2.set("font-weight", "bold");
        buttonStyle2.set("border-radius", "17px");
        buttonStyle2.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
        buttonStyle2.set("margin-right", "auto");
        buttonStyle2.set("margin-left", "auto");


        Style alreadyStyle = hL.getStyle();
        alreadyStyle.set("font-family", "Arial");
        alreadyStyle.set("font-size", "16px");
        alreadyStyle.set("font-weight", "bold");
        alreadyStyle.set("margin-right", "auto");
        alreadyStyle.set("margin-left", "auto");

        Style streetNumbers= streetNumber.getStyle();
        streetNumbers.set("font-family", "Arial");
        streetNumbers.set("font-size", "15px");
        streetNumbers.set("margin-right", "auto");
        streetNumbers.set("margin-left", "auto");

        Style streetNameTextField = streetName.getStyle();
        streetNameTextField.set("font-family", "Arial");
        streetNameTextField.set("font-size", "15px");
        streetNameTextField.set("margin-right", "auto");
        streetNameTextField.set("margin-left", "auto");

        Style suburbTextField = suburb.getStyle();
        suburbTextField.set("font-family", "Arial");
        suburbTextField.set("font-size", "15px");
        suburbTextField.set("margin-right", "auto");
        suburbTextField.set("margin-left", "auto");

        Style cityTextField = city.getStyle();
        cityTextField.set("font-family", "Arial");
        cityTextField.set("font-size", "15px");
        cityTextField.set("margin-right", "auto");
        cityTextField.set("margin-left", "auto");

        Style provinceTextField = province.getStyle();
        provinceTextField.set("font-family", "Arial");
        provinceTextField.set("font-size", "15px");
        provinceTextField.set("margin-right", "auto");
        provinceTextField.set("margin-left", "auto");

        Style countryTextField = country.getStyle();
        countryTextField.set("font-family", "Arial");
        countryTextField.set("font-size", "15px");
        countryTextField.set("margin-right", "auto");
        countryTextField.set("margin-left", "auto");

        Style postalCodeTextField = postalCode.getStyle();
        postalCodeTextField.set("font-family", "Arial");
        postalCodeTextField.set("font-size", "15px");
        postalCodeTextField.set("margin-right", "auto");
        postalCodeTextField.set("margin-left", "auto");


        setMargin(true);

        mainframe.add(hL);
        mainframe.add(firstName);
        mainframe.add(lastName);
        mainframe.add(phoneNumber);
        mainframe.add(email);
        mainframe.add(password);
        mainframe.add(streetNumber);
        mainframe.add(streetName);
        mainframe.add(suburb);
        mainframe.add(city);
        mainframe.add(province);
        mainframe.add(country);
        mainframe.add(postalCode);
        mainframe.add(btnSubmit);
        mainframe.add(btnNext);
        add(mainframe);

    }

//    public void createLoyaltyCustomer(LoyaltyCustomer loyaltyCustomer){
//        LoyaltyCustomerApi loyaltyCustomerApi = new LoyaltyCustomerApi();
//        loyaltyCustomerApi.createLoyaltyCustomer(loyaltyCustomer);
//    }

    public LoyaltyCustomer setValues(){
        //customer
        String firstNameValue = firstName.getValue();
        String lastNameValue = lastName.getValue();
        String phoneValue = phoneNumber.getValue();
        String emailValue = email.getValue();
        String passwordValue = password.getValue();

        //address
        String streetNumberValue =  streetNumber.getValue();
        String streetNameValue = streetName.getValue();
        String suburbValue = suburb.getValue();
        String cityValue = city.getValue();
        String provinceValue = province.getValue();
        String countryValue = country.getValue();
        String postalCodeValue = postalCode.getValue();


        LocalDate date = LocalDate.now();

        Address address =  AddressFactory.buildAddress(
                streetNumberValue,
                streetNameValue,
                suburbValue,
                cityValue,
                provinceValue,
                countryValue,
                postalCodeValue,
                AddressType.RESIDENTIAL_HOME
        );

        Customer customer = CustomerFactory.buildCustomer(
                firstNameValue,
                lastNameValue,
                phoneValue,
                address
        );

        LoyaltyCustomer data = LoyaltyCustomerFactory.createLoyaltyCustomer(
                firstNameValue,
                lastNameValue,
                phoneValue,
                address,
                date,
                10,
                passwordValue,
                emailValue
        );
        return data;
    }
    public Boolean customerErrors() {
        String firstNameValue = firstName.getValue();
        String lastNameValue = lastName.getValue();
        String phoneValue = phoneNumber.getValue();
        String emailValue = email.getValue();
        String passwordValue = password.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() ||
                phoneNumber.isEmpty() || emailValue.isEmpty()  || password.isEmpty()) {
            Notification.show("Please enter in all the required fields");
            return true;
        }
        if(!firstNameValue.matches("[a-zA-Z]+") || !lastNameValue.matches("[a-zA-Z]+")){
            throw new InputMismatchException(("Invalid input, please only enter in letters for your firstname or surname"));
        }
        if(phoneValue.length() != 10){
            Notification.show("The PHONE NUMBER length is below 10");
            return true;
        }
        if(passwordValue.length() < 8){
            Notification.show("The password length is below 8");
            return true;
        }
//        if(!emailValue.matches("[^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$]+") ){
//            Notification.show("Please enter a  valid email");
//            throw new InputMismatchException(("Invalid input, please only enter in letters for your Street or Suburb"));
//        }

        return false;
    }
    public Boolean addressErrors() {
        //address
        String streetNumberValue =  streetNumber.getValue();
        String streetNameValue = streetName.getValue();
        String suburbValue = suburb.getValue();
        String cityValue = city.getValue();
        String provinceValue = province.getValue();
        String countryValue = country.getValue();
        String postalCodeValue = postalCode.getValue();

        if (streetNumber.isEmpty() || streetName.isEmpty() || suburb.isEmpty() || city.isEmpty()
                || country.isEmpty() || province.isEmpty() || postalCode.isEmpty()) {
            Notification.show("Please enter in all the required fields");
            return true;
        }

        if(!streetNameValue.matches("[a-zA-Z]+") || !suburbValue.matches("[a-zA-Z]+")
        || !provinceValue.matches("[a-zA-Z]+") || !cityValue.matches("[a-zA-Z]+")
                || !countryValue.matches("[a-zA-Z]+")){
            throw new InputMismatchException(("Invalid input, please only enter in letters for your Street or Suburb"));
        }

        if (postalCodeValue.length() > 4){
            Notification.show("Invalid Postal Code, please try again");
            return true;
        }
        if(streetNumberValue.length() < 0){
            Notification.show("The Street Number is invalid");
            return true;
        }
        return false;
    }

    public void setStyle(){

//        //customer
//        Style firstNameTextField = firstName.getStyle();
//        firstNameTextField.set("font-family", "Arial");
//        firstNameTextField.set("font-size", "15px");
//        firstNameTextField.set("margin-right", "auto");
//        firstNameTextField.set("margin-left", "auto");
//
//        Style lastNameTextField = lastName.getStyle();
//        lastNameTextField.set("font-family", "Arial");
//        firstNameTextField.set("font-size", "15px");
//        lastNameTextField.set("margin-right", "auto");
//        lastNameTextField.set("margin-left", "auto");
//
//        Style emailTextField = phoneNumber.getStyle();
//        emailTextField.set("font-family", "Arial");
//        emailTextField.set("font-size", "15px");
//        emailTextField.set("margin-right", "auto");
//        emailTextField.set("margin-left", "auto");
//
//        Style usernameTextField = username.getStyle();
//        usernameTextField.set("font-family", "Arial");
//        usernameTextField.set("font-size", "15px");
//        usernameTextField.set("margin-right", "auto");
//        usernameTextField.set("margin-left", "auto");
//
//        Style passwordTextField = password.getStyle();
//        passwordTextField.set("font-family", "Arial");
//        passwordTextField.set("font-size", "15px");
//        passwordTextField.set("margin-right", "auto");
//        passwordTextField.set("margin-left", "auto");
//
//        //address
//        Style buttonStyle = btnSubmit.getStyle();
//        buttonStyle.set("color", "white");
//        buttonStyle.set("background-color", "#000000");
//        buttonStyle.set("font-family", "Arial");
//        buttonStyle.set("font-size", "16px");
//        buttonStyle.set("font-weight", "bold");
//        buttonStyle.set("border-radius", "17px");
//        buttonStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
//        buttonStyle.set("margin-right", "auto");
//        buttonStyle.set("margin-left", "auto");
//
//        Style alreadyStyle = hL.getStyle();
//        alreadyStyle.set("font-family", "Arial");
//        alreadyStyle.set("font-size", "16px");
//        alreadyStyle.set("font-weight", "bold");
//        alreadyStyle.set("margin-right", "auto");
//        alreadyStyle.set("margin-left", "auto");
//
//        Style streetNumbers= streetNumber.getStyle();
//        streetNumbers.set("font-family", "Arial");
//        streetNumbers.set("font-size", "15px");
//        streetNumbers.set("margin-right", "auto");
//        streetNumbers.set("margin-left", "auto");
//
//        Style streetNameTextField = streetName.getStyle();
//        streetNameTextField.set("font-family", "Arial");
//        streetNameTextField.set("font-size", "15px");
//        streetNameTextField.set("margin-right", "auto");
//        streetNameTextField.set("margin-left", "auto");
//
//        Style suburbTextField = suburb.getStyle();
//        suburbTextField.set("font-family", "Arial");
//        suburbTextField.set("font-size", "15px");
//        suburbTextField.set("margin-right", "auto");
//        suburbTextField.set("margin-left", "auto");
//
//        Style cityTextField = city.getStyle();
//        cityTextField.set("font-family", "Arial");
//        cityTextField.set("font-size", "15px");
//        cityTextField.set("margin-right", "auto");
//        cityTextField.set("margin-left", "auto");
//
//        Style provinceTextField = province.getStyle();
//        provinceTextField.set("font-family", "Arial");
//        provinceTextField.set("font-size", "15px");
//        provinceTextField.set("margin-right", "auto");
//        provinceTextField.set("margin-left", "auto");
//
//        Style countryTextField = country.getStyle();
//        countryTextField.set("font-family", "Arial");
//        countryTextField.set("font-size", "15px");
//        countryTextField.set("margin-right", "auto");
//        countryTextField.set("margin-left", "auto");
//
//        Style postalCodeTextField = postalCode.getStyle();
//        postalCodeTextField.set("font-family", "Arial");
//        postalCodeTextField.set("font-size", "15px");
//        postalCodeTextField.set("margin-right", "auto");
//        postalCodeTextField.set("margin-left", "auto");
    }
}
