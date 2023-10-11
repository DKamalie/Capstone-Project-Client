package com.example.application.views.vehicle;

import com.example.application.api.VehicleApi;
import com.example.application.domain.Vehicle;
import com.example.application.factory.VehicleFactory;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Set;

@PageTitle("Vehicle")
@Route(value = "vehicle", layout = MainLayout.class)
public class VehicleView extends VerticalLayout {


    private TextField vehicleId;
    private TextField vehicleType;
    private TextField make;
    private TextField model;
    private TextField year;
    private TextField colour;
    private Button saveButton;
    private Button updateButton;
    private Button deleteButton;
    private Button viewAllButton;
    private Button resetButton;
    private Div viewContainer;
    private boolean isEditing = false;

    VehicleApi getAll = new VehicleApi();

    public VehicleView() {

        vehicleId = new TextField("Vehicle Id: ");
        vehicleId.setWidth("300px");
        vehicleId.setPlaceholder("Enter in the vehicle Id");

        vehicleType = new TextField("Vehicle type: ");
        vehicleType.setWidth("300px");
        vehicleType.setPlaceholder("Enter in the vehicle type");

        make = new TextField("Make: ");
        make.setWidth("300px");
        make.setPlaceholder("Enter in the make");

        model = new TextField("Model: ");
        model.setWidth("300px");
        model.setPlaceholder("Enter in the model");

        year = new TextField("Year: ");
        year.setWidth("300px");
        year.setPlaceholder("Enter in the year");

        colour = new TextField("Colour: ");
        colour.setWidth("300px");
        colour.setPlaceholder("Enter in the colour");

        saveButton = new Button("Save");
        saveButton.setWidth("300px");

        updateButton = new Button("Update");
        updateButton.setWidth("300px");
        updateButton.setEnabled(true);

        deleteButton = new Button("Delete");
        deleteButton.setWidth("300px");

        viewAllButton = new Button("View all vehicles");
        viewAllButton.setWidth("300px");

        resetButton = new Button("Reset");
        resetButton.setWidth("300px");

        VerticalLayout inputContainer = new VerticalLayout(vehicleId, vehicleType, make, model, year, colour, saveButton, updateButton, deleteButton, viewAllButton, resetButton);
        inputContainer.setAlignItems(Alignment.BASELINE);

        viewContainer = new Div();

        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        centeringLayout.setSizeFull();

        centeringLayout.add(viewContainer);

        Div dataContainer = new Div(viewContainer);


        Div dataMarginContainer = new Div(dataContainer);
        dataMarginContainer.getStyle()
                .set("margin-left", "200px");

        HorizontalLayout mainContainer = new HorizontalLayout(inputContainer, dataMarginContainer);
        mainContainer.setDefaultVerticalComponentAlignment(Alignment.CENTER);




        saveButton.addClickListener(e -> {//this is for creating a vehicle and saving it to the database
            try {
                boolean hasErrors = checkErrors();

                if (!hasErrors) {
                    Vehicle vehicleSave = setVehicleValues();
                    createVehicle(vehicleSave);
                    Notification.show("Vehicle saved");
                }
            } catch (Exception exception) {
                Notification.show(exception.getMessage());
            }
        });


        vehicleId.addKeyDownListener(Key.ENTER, event -> {//read method
            try {
                TextField source = (TextField) event.getSource();
                String enteredValue = source.getValue();

                if (!enteredValue.isEmpty()) {
                    Integer enteredVehicleId = Integer.valueOf(enteredValue);

                    // Call the readVehicleId method to fetch vehicle data
                    Vehicle vehicleData = readVehicleId(enteredVehicleId);

                    // Update the other text fields with the retrieved data
                    if (vehicleData != null) {
                        updateVehicleFields(vehicleData);
                        vehicleId.setEnabled(false);
                        Notification.show("Vehicle Id read successfully");
                    } else {
                        // Handle the case where the entered vehicleId does not exist
                        Notification.show("Vehicle with ID " + enteredVehicleId + " not found");
                    }
                }
            } catch (NumberFormatException e) {
                // Handle the case where the entered value is not a valid integer
                Notification.show("Please enter a valid vehicle ID as a number.");
            } catch (Exception e) {
                // Handle any other exceptions that may occur
                Notification.show("An error occurred: " + e.getMessage());
            }
        });


        updateButton.addClickListener(e -> {//use for update method
            try {
                System.out.println(isEditing);

                    if (isEditing == false) {
                        Vehicle updatedVehicle = updateSetVehicleValues();
                        updateVehicle(updatedVehicle);

                        Notification.show("Vehicle updated successfully");
                    }
                } catch (Exception ex) {
                    Notification.show("An error occurred during the update: " + ex.getMessage());
                }
        });

        deleteButton.addClickListener(e ->{//use for delete method
                try {
                    String vehicleIdValue = vehicleId.getValue();
                    if (!vehicleIdValue.isEmpty()) {
                        Integer id = Integer.valueOf(vehicleIdValue);
                        deleteVehicleId(id);
                        Notification.show("Vehicle deleted successfully");
                        clearFormFields();
                    } else {
                        Notification.show("Please enter a vehicle ID.");
                    }
                } catch (NumberFormatException ex) {
                    Notification.show("Please enter a valid vehicle ID as a number.");
                } catch (Exception ex) {
                    Notification.show("An error occurred during the delete: " + ex.getMessage());
                }
        });


        viewAllButton.addClickListener(event -> {//use for getAll method
            try {
                Set<Vehicle> vehicles = getAll.getAll();


                viewContainer.removeAll();


                vehicles.forEach(vehicle -> {
                    viewContainer.add(createVehicleSpan(vehicle));
                });


            } catch (Exception e) {

                Notification.show("Failed to retrieve vehicles. Please try again later." + e.getMessage());
            }
        });

        resetButton.addClickListener(event -> {//use for getAll method
            try {


                clearFormFields();

            } catch (Exception e) {

                Notification.show("Failed to clear the fields." + e.getMessage());
            }
        });



        Style buttonStyle = saveButton.getStyle();
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

        Style buttonStyle3 = deleteButton.getStyle();
        buttonStyle3.set("color", "white");
        buttonStyle3.set("background-color", "#000000");
        buttonStyle3.set("font-family", "Arial");
        buttonStyle3.set("font-size", "16px");
        buttonStyle3.set("font-weight", "bold");
        buttonStyle3.set("border-radius", "17px");
        buttonStyle3.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style buttonStyle4 = viewAllButton.getStyle();
        buttonStyle4.set("color", "white");
        buttonStyle4.set("background-color", "#000000");
        buttonStyle4.set("font-family", "Arial");
        buttonStyle4.set("font-size", "16px");
        buttonStyle4.set("font-weight", "bold");
        buttonStyle4.set("border-radius", "17px");
        buttonStyle4.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style buttonStyle5 = resetButton.getStyle();
        buttonStyle5.set("color", "white");
        buttonStyle5.set("background-color", "#000000");
        buttonStyle5.set("font-family", "Arial");
        buttonStyle5.set("font-size", "16px");
        buttonStyle5.set("font-weight", "bold");
        buttonStyle5.set("border-radius", "17px");
        buttonStyle5.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        setMargin(true);
        add(mainContainer);



    }

    public void createVehicle(Vehicle vehicle) {//use for create method
        VehicleApi vehicleApi = new VehicleApi();
        vehicleApi.createVehicle(vehicle);
    }

    public Vehicle readVehicleId(Integer vehicleId) {//use for read method
        VehicleApi readVehicleId = new VehicleApi();
        return readVehicleId.readVehicle(vehicleId);
    }

    public void updateVehicle(Vehicle vehicle) {//use for the update method
        VehicleApi updateVehicle = new VehicleApi();
        updateVehicle.updateVehicle(vehicle);
    }

    public void deleteVehicleId(Integer vehicleId){//use for delete method
        VehicleApi deleteVehicleId = new VehicleApi();
        deleteVehicleId.deleteVehicle(vehicleId);
    }

    public void updateVehicleFields(Vehicle vehicle) {//this is not an update method, this is for the read method
        vehicleType.setValue(vehicle.getVehicleType());
        make.setValue(vehicle.getMake());
        model.setValue(vehicle.getModel());
        year.setValue(vehicle.getYear());
        colour.setValue(vehicle.getColour());
    }

    public Vehicle setVehicleValues() {//use for create method

        String vehicleTypeValue = vehicleType.getValue();
        String makeValue = make.getValue();
        String modelValue = model.getValue();
        String yearValue = year.getValue();
        String colourValue = colour.getValue();

        Vehicle getVehicleData = VehicleFactory.createVehicle(vehicleTypeValue, makeValue, modelValue, yearValue, colourValue);

        return getVehicleData;
    }


    public Vehicle updateSetVehicleValues() {//use for update method


        Integer vehicleIdValue = Integer.valueOf(vehicleId.getValue());
        String vehicleTypeValue = vehicleType.getValue();
        String makeValue = make.getValue();
        String modelValue = model.getValue();
        String yearValue = year.getValue();
        String colourValue = colour.getValue();

        Vehicle updateVehicleData = VehicleFactory.buildVehicle(
                vehicleIdValue,
                vehicleTypeValue,
                makeValue,
                modelValue,
                yearValue,
                colourValue);

        return updateVehicleData;

    }



    public boolean checkErrors() {//checks errors
        String vehicleTypeValue = vehicleType.getValue();
        String makeValue = make.getValue();
        String modelValue = model.getValue();
        String yearValue = year.getValue();
        String colourValue = colour.getValue();

        if (vehicleTypeValue.isEmpty() || makeValue.isEmpty() || modelValue.isEmpty() || yearValue.isEmpty() || colourValue.isEmpty()) {
            Notification.show("Please enter in all the fields.");
            return true;
        }

        if (!vehicleTypeValue.matches("[a-zA-Z ]+") || !makeValue.matches("[a-zA-Z ]+") || !modelValue.matches("[a-zA-Z ]+") || !colourValue.matches("[a-zA-Z ]+")) {
            Notification.show("Invalid input. please only enter letters.");
            return true;
        }

        if (!yearValue.matches("^[0-9]+$")) {
            Notification.show("Invalid input, please only enter in numbers.");
            return true;
        }

        if (!(yearValue.length() == 4)) {
            Notification.show("Invalid input, please enter in a valid year.");
            return true;
        }

        return false;


    }

    private Div createVehicleSpan(Vehicle vehicle) {
        Div outerDiv = new Div();
        outerDiv.getStyle().set("display", "flex");
        outerDiv.getStyle().set("justify-content", "center");
        outerDiv.getStyle().set("margin-top", "15px");
        outerDiv.getStyle().set("margin-right", "300px");

        Div innerDiv = new Div();
        innerDiv.getStyle().set("display", "flex");
        innerDiv.getStyle().set("flex-direction", "row");
        innerDiv.getStyle().set("justify-content", "space-between");

        createDataField(innerDiv, "Vehicle ID ", String.valueOf(vehicle.getVehicleId()));
        createDataField(innerDiv, "Type ", vehicle.getVehicleType());
        createDataField(innerDiv, "Make ", vehicle.getMake());
        createDataField(innerDiv, "Model ", vehicle.getModel());
        createDataField(innerDiv, "Year ", vehicle.getYear());
        createDataField(innerDiv, "Colour ", vehicle.getColour());

        outerDiv.add(innerDiv);

        return outerDiv;
    }

    private void createDataField(Div container, String label, String value) {
        Span labelSpan = new Span(label);
        labelSpan.getStyle().set("font-weight", "bold");
        Span valueSpan = new Span(value);

        Div dataField = new Div(labelSpan, valueSpan);
        dataField.getStyle().set("margin-right", "40px");

        container.add(dataField);
    }
    private void clearFormFields() {//clear text fields
        vehicleId.clear();
        vehicleType.clear();
        make.clear();
        model.clear();
        year.clear();
        colour.clear();

    }


}
