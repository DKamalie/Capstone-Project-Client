package com.example.application.views.checkout;

import com.example.application.api.AddressApi;
import com.example.application.api.EmployeeApi;
import com.example.application.domain.Address;
import com.example.application.domain.Employee;
import com.example.application.domain.LoyaltyCustomer;
import com.example.application.domain.Pizza;
import com.example.application.factory.AddressFactory;
import com.example.application.factory.PizzaFactory;
import com.example.application.views.MainLayout;
import com.example.application.views.menu.MenuView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;


@PageTitle("Address Update")
@Route(value = "addressupdate", layout = MainLayout.class)
@Push
public class AddressUpdateView extends FormLayout {
    private TextField city = new TextField("City");
    private TextField country = new TextField("Country");
    private TextField flatNumber = new TextField("Flat Number");
    private TextField postalCode = new TextField("Postal Code");
    private TextField province = new TextField("Province");
    private TextField streetName = new TextField("Street Name");
    private TextField streetNumber = new TextField("Street Number");
    private TextField suburb = new TextField("Suburb");

    private Button saveButton = new Button("Update");
    private Button backButton = new Button("Back");
    private Integer addressId;

    public AddressUpdateView() {
        add(form());
    }

    public Component form(){

        saveButton.addClickListener(event -> save());
        saveButton.addClassNames("custom-button");

        backButton.addClassNames("custom-button");
        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(CheckoutView.class));
        });

        city.addClassName("my-textfield");
        country.addClassName("my-textfield");
        flatNumber.addClassName("my-textfield");
        postalCode.addClassName("my-textfield");
        province.addClassName("my-textfield");
        streetName.addClassName("my-textfield");
        streetNumber.addClassName("my-textfield");
        suburb.addClassName("my-textfield");

        if (isUserLoggedIn()) {
            LoyaltyCustomer loggedInCustomer = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("loggedInCustomer");

            city.setPlaceholder(loggedInCustomer.getAddress().getCity());
            country.setPlaceholder(loggedInCustomer.getAddress().getCountry());
            flatNumber.setPlaceholder(loggedInCustomer.getAddress().getFlatNumber());
            postalCode.setPlaceholder(loggedInCustomer.getAddress().getPostalCode());
            province.setPlaceholder(loggedInCustomer.getAddress().getProvince());
            streetName.setPlaceholder(loggedInCustomer.getAddress().getStreetName());
            streetNumber.setPlaceholder(loggedInCustomer.getAddress().getStreetNumber());
            suburb.setPlaceholder(loggedInCustomer.getAddress().getSuburb());


        }

        H2 header = new H2("Update Address");
        Section checkoutForm = new Section();
        checkoutForm.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN,
                LumoUtility.Flex.GROW, LumoUtility.BorderRadius.LARGE, LumoUtility.Padding.LARGE);
        checkoutForm.add(header);

        FormLayout formLayout = new FormLayout();

        formLayout.add(city, country, flatNumber, postalCode, province, streetName, streetNumber, suburb, saveButton,backButton);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2)
        );
        formLayout.getStyle().set("maxWidth", "400px");
        formLayout.getStyle().set("justify-content","center");

        checkoutForm.add(header);
        checkoutForm.add(formLayout);

        return checkoutForm;
    }
    private boolean isUserLoggedIn() {

        return VaadinSession.getCurrent().getAttribute("loggedInCustomer") != null;
    }

    private void save() {
        if (isUserLoggedIn()) {
            if(checkErrors() == false) {
                updateEmployee(updateSetAddressValues());
                getUI().ifPresent(ui -> ui.navigate(CheckoutView.class));
            }
        }
    }

    public void updateEmployee(Address address) {//use for the update method
        AddressApi updateAddress = new AddressApi();
        updateAddress.updateAddress(address);
    }

    public Address updateSetAddressValues() {//use for update method

        if (isUserLoggedIn()) {
            LoyaltyCustomer loggedInCustomer = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("loggedInCustomer");

            addressId = loggedInCustomer.getAddress().getAddressId();

        }

        String city = this.city.getValue();
        String country = this.country.getValue();
        String flatNumber = this.flatNumber.getValue();
        String postalCode = this.postalCode.getValue();
        String province = this.province.getValue();
        String streetName = this.streetName.getValue();
        String streetNumber = this.streetNumber.getValue();
        String suburb = this.suburb.getValue();

        Address updateAddress = AddressFactory.createAddress(
                addressId,
                streetNumber,
                streetName,
                flatNumber,
                suburb,
                city,
                province,
                country,
                postalCode,
                null);


        return updateAddress;
    }

    public boolean checkErrors() {

        String city = this.city.getValue();
        String country = this.country.getValue();
        String flatNumber = this.flatNumber.getValue();
        String postalCode = this.postalCode.getValue();
        String province = this.province.getValue();
        String streetName = this.streetName.getValue();
        String streetNumber = this.streetNumber.getValue();
        String suburb = this.suburb.getValue();


        if (city.isEmpty() || country.isEmpty() || flatNumber.isEmpty() ||  postalCode.isEmpty()  || province.isEmpty() || streetName.isEmpty() ||  streetNumber.isEmpty()  ||  suburb.isEmpty()) {
            Notification.show("Please enter in all the fields.");
            return true;
        }

        if (!country.matches("[a-zA-Z ]+") || !province.matches("[a-zA-Z ]+") || !streetName.matches("[a-zA-Z ]+")  || !suburb.matches("[a-zA-Z ]+")) {
            Notification.show("Invalid input. please only enter letters.");
            return true;
        }

        int postalCodeInt = Integer.parseInt(postalCode);

        if (postalCodeInt <= 0) {
            Notification.show("Invalid input, postal code must contain numbers.");
            return true;
        }

        if (!postalCode.matches("^[0-9]+$")) {
            Notification.show("Invalid input, please only enter in numbers.");
            return true;
        }
        return false;
    }
}
