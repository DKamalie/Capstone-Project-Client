package com.example.application.views.vehicle;

import com.example.application.api.VehicleApi;
import com.example.application.domain.Vehicle;
import com.example.application.factory.VehicleFactory;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

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
    private Button readUpdateButton;
    private boolean isEditing = false;

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
        updateButton.setEnabled(false);

        readUpdateButton = new Button("Read update");
        readUpdateButton.setWidth("300px");

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
                        disableEditing();
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


        updateButton.addClickListener(e -> {
            try {

                    if (isEditing) {
                        Vehicle updatedVehicle = updateSetVehicleValues();
                        updateVehicle(updatedVehicle);
                        Notification.show("Vehicle updated successfully");
                        disableEditing(); // Disable editing after updating
                    }
                } catch (Exception ex) {
                    Notification.show("An error occurred during the update: " + ex.getMessage());
                }
        });


/*
        vehicleId.addKeyDownListener(Key.ENTER, event -> {
            try {
                TextField source = (TextField) event.getSource();
                String enteredValue = source.getValue();

                if (!enteredValue.isEmpty()) {
                    Integer enteredVehicleId = Integer.valueOf(enteredValue);

                    // Perform update action
                    Vehicle updatedVehicle = setVehicleValues();
                    updateVehicle(updatedVehicle);
                    Notification.show("Vehicle Id read successfully");
                    vehicleId.setEnabled(false);
                }
            } catch (NumberFormatException e) {
                Notification.show("Please enter a valid vehicle ID as a number.");
            } catch (Exception e) {
                Notification.show("An error occurred: " + e.getMessage());
            }
        });

 */
/*
        updateButton.addClickListener(e -> {
            try {
                // TextField source = (TextField) event.getSource();
                String enteredValue = vehicleId.getValue();

                if (!enteredValue.isEmpty()) {
                    Integer enteredVehicleId = Integer.valueOf(enteredValue);

                    // Call the readVehicleId method to fetch vehicle data
                    Vehicle vehicleData = readVehicleId(enteredVehicleId);

                    // Update the other text fields with the retrieved data
                    if (vehicleData != null) {
                        updateVehicleFields(vehicleData);
                        vehicleId.setEnabled(false);
                        Notification.show("Vehicle Id read successfully");
                    }  if (isEditing) {


                        // Capture the updated data from the textfields
                        Vehicle updatedVehicle = updateSetVehicleValues();

                        // Perform the update action
                        updateVehicle(updatedVehicle);

                        // Notify the user of a successful update
                        Notification.show("Vehicle updated successfully");

                        // Re-enable editing mode
                        enableEditing();
                    } else {
                        // Handle the case where the entered vehicleId does not exist
                        Notification.show("Vehicle with ID " + enteredVehicleId + " not found");
                    }
                }
            } catch (NumberFormatException eg) {
                // Handle the case where the entered value is not a valid integer
                Notification.show("Please enter a valid vehicle ID as a number.");
            } catch (Exception eg) {
                // Handle any other exceptions that may occur
                Notification.show("An error occurred: " + eg.getMessage());
            }
            /*
            try {
                if (isEditing) {


                    // Capture the updated data from the textfields
                    Vehicle updatedVehicle = updateSetVehicleValues();

                    // Perform the update action
                    updateVehicle(updatedVehicle);

                    // Notify the user of a successful update
                    Notification.show("Vehicle updated successfully");

                    // Re-enable editing mode
                    enableEditing();
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during the update: " + ex.getMessage());
            }


        });

 */



/*

        updateButton.addClickListener(e -> {
            try {
                if (isEditing) {
                    // Only perform the update operation if in editing mode
                    Vehicle updatedVehicle = setVehicleValues();
                    updateVehicle(updatedVehicle);
                    Notification.show("Vehicle updated successfully");
                    enableEditing(); // Re-enable editing mode
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during the update: " + ex.getMessage());
            }
        });

 */


        //styling stuff can come last

        setMargin(true);

        add(vehicleId);
        add(vehicleType);
        add(make);
        add(model);
        add(year);
        add(colour);
        add(saveButton);
        add(updateButton);


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

        Vehicle updateVehicleData = VehicleFactory.buildVehicle(vehicleIdValue, vehicleTypeValue, makeValue, modelValue, yearValue, colourValue);

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

    private void enableEditing() {
        isEditing = true;
        vehicleType.setEnabled(true);
        make.setEnabled(true);
        model.setEnabled(true);
        year.setEnabled(true);
        colour.setEnabled(true);

    }

    private void disableEditing() {
        isEditing = false;
        vehicleType.setEnabled(false);
        make.setEnabled(false);
        model.setEnabled(false);
        year.setEnabled(false);
        colour.setEnabled(false);
    }

}
