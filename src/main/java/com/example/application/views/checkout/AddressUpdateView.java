package com.example.application.views.checkout;

import com.example.application.api.AddressApi;
import com.example.application.api.EmployeeApi;
import com.example.application.domain.*;
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
    private Integer AddressId;
    private TextField city;
    private TextField country;
    private TextField flatNumber;
    private TextField postalCode;
    private TextField province;
    private TextField streetName;
    private TextField streetNumber;
    private TextField suburb;

    private Button updateButton;
    private Button backButton;

    AddressApi getAll = new AddressApi();

    public AddressUpdateView() {
        city = new TextField("City");
        city.setWidth("300px");
        city.setPlaceholder("Enter City");
        city.setRequired(true);

        country = new TextField("Country");
        country.setWidth("300px");
        country.setPlaceholder("Enter Country");
        country.setRequired(true);

        flatNumber = new TextField("Flat Number");
        flatNumber.setWidth("300px");
        flatNumber.setPlaceholder("Enter Flat Number");
        flatNumber.setRequired(true);

        postalCode = new TextField("Postal Code");
        postalCode.setWidth("300px");
        postalCode.setPlaceholder("Enter Postal Code");
        postalCode.setRequired(true);

        province = new TextField("Province");
        province.setWidth("300px");
        province.setPlaceholder("Enter Province");
        province.setRequired(true);

        streetName = new TextField("Street Name");
        streetName.setWidth("300px");
        streetName.setPlaceholder("Enter Street Name");
        streetName.setRequired(true);

        streetNumber = new TextField("Street Number");
        streetNumber.setWidth("300px");
        streetNumber.setPlaceholder("Enter Street Number");
        streetNumber.setRequired(true);

        suburb = new TextField("Suburb");
        suburb.setWidth("300px");
        suburb.setPlaceholder("Enter Suburb");
        suburb.setRequired(true);

        updateButton = new Button("Update");
        updateButton.setWidth("300px");

        backButton = new Button("Back");
        backButton.setWidth("300px");

        FormLayout formLayout = new FormLayout(city, country, flatNumber, postalCode, province, streetName, streetNumber, suburb, updateButton, backButton);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2)
        );
        formLayout.getStyle().set("maxWidth", "400px");

        Main content = new Main();
        content.addClassNames(LumoUtility.Display.GRID, LumoUtility.Gap.XLARGE, LumoUtility.AlignItems.CENTER, LumoUtility.JustifyContent.CENTER, LumoUtility.MaxWidth.SCREEN_MEDIUM,
                LumoUtility.Margin.Horizontal.AUTO, LumoUtility.Padding.Bottom.LARGE, LumoUtility.Padding.Horizontal.LARGE);
         content.add(formLayout);

        updateButton.addClickListener(e -> {//use for update method
            try {

                if (checkErrors() == false) {
                    Address address = updateSetAddressValues();
                    updateAddress(address);

                    Notification.show("Address updated successfully");
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during the update: " + ex.getMessage());
            }
        });

        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(CheckoutView.class));
        });


        Style buttonStyle = backButton.getStyle();
        buttonStyle.set("color", "white");
        buttonStyle.set("background-color", "#000000");
        buttonStyle.set("font-family", "Arial");
        buttonStyle.set("font-size", "16px");
        buttonStyle.set("font-weight", "bold");
        buttonStyle.set("border-radius", "17px");
        buttonStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style buttonStyle2 = updateButton.getStyle();
        buttonStyle2.set("color", "white");
        buttonStyle2.set("background-color", "#000000");
        buttonStyle2.set("font-family", "Arial");
        buttonStyle2.set("font-size", "16px");
        buttonStyle2.set("font-weight", "bold");
        buttonStyle2.set("border-radius", "17px");
        buttonStyle2.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        add(content);
    }

    public void updateAddress(Address address) {//use for the update method
        AddressApi updateAddress = new AddressApi();
        updateAddress.updateAddress(address);
    }

    public Address updateSetAddressValues() {//use for update method

        if (isUserLoggedIn()) {
            LoyaltyCustomer loggedInCustomer = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("loggedInCustomer");
            AddressId = loggedInCustomer.getAddress().getAddressId();
        }

        Integer AddressIdValue = AddressId;
        String cityValue = city.getValue();
        String countryValue = country.getValue();
        String flatNumberValue = flatNumber.getValue();
        String postalCodeValue = postalCode.getValue();
        String provinceValue = province.getValue();
        String streetNameValue = streetName.getValue();
        String streetNumberValue = streetNumber.getValue();
        String suburbValue = suburb.getValue();
        AddressType addressTypeValue = AddressType.RESIDENTIAL_HOME;

        Address updateAddressData = AddressFactory.createAddress(
                AddressIdValue,
                streetNumberValue,
                streetNameValue,
                flatNumberValue,
                suburbValue,
                cityValue,
                provinceValue,
                countryValue,
                postalCodeValue,
                addressTypeValue);


        return updateAddressData;
    }

    private boolean isUserLoggedIn() {
        return VaadinSession.getCurrent().getAttribute("loggedInCustomer") != null;
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
