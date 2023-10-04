//package com.example.application.views.signUp;
//
//import com.example.application.domain.Address;
//import com.example.application.domain.AddressType;
//import com.example.application.domain.Customer;
//import com.example.application.domain.LoyaltyCustomer;
//import com.example.application.factory.AddressFactory;
//import com.example.application.factory.CustomerFactory;
//import com.example.application.factory.LoyaltyCustomerFactory;
//import com.example.application.views.MainLayout;
//import com.example.application.views.home.HomeView;
//import com.example.application.views.login.LoginView;
//import com.vaadin.flow.component.Text;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.PasswordField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.dom.Style;
//import com.vaadin.flow.router.*;
//
//import java.time.LocalDate;
//import java.util.InputMismatchException;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.router.RouterLink;
//
///*
// Sign Up Address View.java
// Author: Keenan Meyer (220194920)
// Date: 9th September (last updated) 2023
//*/
//@PageTitle("Sign Up Address")
//@Route(value = "sign-up Address", layout = MainLayout.class)
//public class signUpAddress extends VerticalLayout{
//    private Text alreadyWithUs;
//    private RouterLink loginLink;
//    private TextField streetNumber;
//    private TextField streetName;
//    private TextField suburb;
//    private TextField city;
//    private TextField province;
//    private PasswordField country;
//    private TextField postalCode;
//    private Button btnSubmit;
//    private HorizontalLayout hL;
//    private VerticalLayout mainframe;
//
//
//    public signUpAddress() {
//
//        alreadyWithUs = new Text("Missed something?");
//        loginLink = new RouterLink("Go Back", signUp.class);
//        hL = new HorizontalLayout();
//        mainframe = new VerticalLayout();
//        hL.add(alreadyWithUs, loginLink);
//        streetNumber = new TextField("street Number:");
//        streetNumber.setWidth("300px");
//        streetNumber.setPlaceholder("Enter your street Number");
//        streetName = new TextField("street Name:");
//        streetName.setWidth("300px");
//        streetName.setPlaceholder("Enter your street name");
//        suburb = new TextField("suburb:");
//        suburb.setWidth("300px");
//        suburb.setPlaceholder("Enter your suburb");
//        city = new TextField("City:");
//        city.setWidth("300px");
//        city.setPlaceholder("Enter your City");
//        province = new TextField("Province:");
//        province.setWidth("300px");
//        province.setPlaceholder("Enter your province");
//        country = new PasswordField("country:");
//        country.setWidth("300px");
//        country.setPlaceholder("Enter your country");
//        postalCode = new TextField("PostalCode:");
//        postalCode.setWidth("300px");
//        postalCode.setPlaceholder("Enter your postalCode");
//        btnSubmit = new Button("Submit");
//        btnSubmit.setWidth("300px");
//
//
//        btnSubmit.addClickListener(e -> {
//            try {
//                getUI().ifPresent(ui -> ui.navigate(HomeView.class));
//
//                String streetNameValue = streetName.getValue();
//                String suburbValue = suburb.getValue();
//                String cityValue = city.getValue();
//                String provinceValue = province.getValue();
//                String countryValue = country.getValue();
//                String postalCodeValue = postalCode.getValue();
//                String streetNumberValue =  streetName.getValue();
//
//
////                String firstNameValue = firstName.getValue();
////                String lastNameValue = lastName.getValue();
////                String phoneValue = phoneNumber.getValue();
////                String usernameValue = username.getValue();
////                String passwordValue = password.getValue();
//
//
//                LocalDate date = LocalDate.now();
//
//                Address address =  AddressFactory.buildAddress(
//                        streetNumberValue,
//                        streetNameValue,
//                        suburbValue,
//                        cityValue,
//                        provinceValue,
//                        countryValue,
//                        postalCodeValue,
//                        AddressType.RESIDENTIAL_HOME
//                );
//
//                Customer customer = CustomerFactory.buildCustomer(
//                        "",
//                        "",
//                        "",
//                        address
//                );
//
//                LoyaltyCustomer data = LoyaltyCustomerFactory.createLoyaltyCustomer(
//                        "",
//                        "",
//                        "",
//                        address,
//                        date,
//                        0,
//                        "",
//                        ""
//
//                );
//
//            } catch (Exception exception) {
//                Notification.show(exception.getMessage());
//            }
//        });
//
//
//
//        setMargin(true);
//
//        mainframe.add(hL);
//        mainframe.add(streetNumber);
//        mainframe.add(streetName);
//        mainframe.add(suburb);
//        mainframe.add(city);
//        mainframe.add(province);
//        mainframe.add(country);
//        mainframe.add(postalCode);
//        mainframe.add(btnSubmit);
//        add(mainframe);
//
//    }
//
//    public LoyaltyCustomer click(LoyaltyCustomer loyaltyCustomer){
////        loyaltyCustomer.setDateJoined();
////        loyaltyCustomer.setDiscounts(13.00);
////        loyaltyCustomer.setEmail();
////        loyaltyCustomer.setPassword();
//        return loyaltyCustomer;
//    }
//    public Boolean Errors() {
//
//        String streetNameValue = streetName.getValue();
//        String suburbValue = suburb.getValue();
//        String cityValue = city.getValue();
//        String provinceValue = province.getValue();
//        String countryValue = country.getValue();
//        String postalCodeValue = postalCode.getValue();
//
//        if (streetNumber.isEmpty() || streetName.isEmpty() || suburb.isEmpty() || city.isEmpty()  || province.isEmpty() || postalCode.isEmpty()) {
//            Notification.show("Please enter in all the required fields");
//            return true;
//        }
//
////        if(!streetName.matches("[a-zA-Z]+") || !streetNameValue.matches("[a-zA-Z]+") ){
////            throw new InputMismatchException(("Invalid input, please only enter in letters for your first name or last name"));
////        }
//
////        if (country.length() < 10){
////            Notification.show("Invalid Phone Number, please try again");
////            return true;
////        }
////        if(country.length() < 8){
////            Notification.show("The password length is below 8");
////            return true;
////        }
//        return false;
//    }
//
//    public void setStyle(){
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
//        Style firstNameTextField = streetName.getStyle();
//        firstNameTextField.set("font-family", "Arial");
//        firstNameTextField.set("font-size", "15px");
//        firstNameTextField.set("margin-right", "auto");
//        firstNameTextField.set("margin-left", "auto");
//
//        Style lastNameTextField = suburb.getStyle();
//        lastNameTextField.set("font-family", "Arial");
//        firstNameTextField.set("font-size", "15px");
//        lastNameTextField.set("margin-right", "auto");
//        lastNameTextField.set("margin-left", "auto");
//
//        Style emailTextField = city.getStyle();
//        emailTextField.set("font-family", "Arial");
//        emailTextField.set("font-size", "15px");
//        emailTextField.set("margin-right", "auto");
//        emailTextField.set("margin-left", "auto");
//
//        Style usernameTextField = province.getStyle();
//        usernameTextField.set("font-family", "Arial");
//        usernameTextField.set("font-size", "15px");
//        usernameTextField.set("margin-right", "auto");
//        usernameTextField.set("margin-left", "auto");
//
//        Style passwordTextField = country.getStyle();
//        passwordTextField.set("font-family", "Arial");
//        passwordTextField.set("font-size", "15px");
//        passwordTextField.set("margin-right", "auto");
//        passwordTextField.set("margin-left", "auto");
//
//        Style postalCodeTextField = postalCode.getStyle();
//        postalCodeTextField.set("font-family", "Arial");
//        postalCodeTextField.set("font-size", "15px");
//        postalCodeTextField.set("margin-right", "auto");
//        postalCodeTextField.set("margin-left", "auto");
//    }
//}
