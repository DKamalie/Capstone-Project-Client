package com.example.application.views.admin.topping;

import com.example.application.api.ToppingApi;
import com.example.application.domain.Topping;
import com.example.application.factory.ToppingFactory;
import com.example.application.views.MainLayout;
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

import java.util.ArrayList;

@PageTitle("Topping")
@Route(value = "topping", layout = MainLayout.class)
public class ToppingAdminView extends VerticalLayout {
    private TextField toppingId;
    private TextField name;
    private TextField description;
    private TextField quantity;
    private TextField price;
    private Button saveButton;
    private Button updateButton;
    private Button deleteButton;
    private Button viewAllButton;
    private Button resetButton;
    private Div viewContainer;
    private boolean isEditing = false;
    ToppingApi getAll = new ToppingApi();

    public ToppingAdminView() {

        toppingId = new TextField("Topping Id: ");
        toppingId.setWidth("300px");
        toppingId.setPlaceholder("Enter in the topping Id");

        name = new TextField("Name: ");
        name.setWidth("300px");
        name.setPlaceholder("Enter in the name");

        description = new TextField("Description: ");
        description.setWidth("300px");
        description.setPlaceholder("Enter in the description");

        quantity = new TextField("Quantity: ");
        quantity.setWidth("300px");
        quantity.setPlaceholder("Enter in the quantity");

        price = new TextField("Price R: ");
        price.setWidth("300px");
        price.setPlaceholder("Enter in the price");

        saveButton = new Button("Save");
        saveButton.setWidth("300px");

        updateButton = new Button("Update");
        updateButton.setWidth("300px");
        updateButton.setEnabled(true);

        deleteButton = new Button("Delete");
        deleteButton.setWidth("300px");

        viewAllButton = new Button("View all toppings");
        viewAllButton.setWidth("300px");

        resetButton = new Button("Reset");
        resetButton.setWidth("300px");


        VerticalLayout inputContainer = new VerticalLayout(toppingId, name, description, quantity, price, saveButton, updateButton, deleteButton, viewAllButton, resetButton);
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
                    Topping toppingSave = setToppingValues();
                    createTopping(toppingSave);
                    Notification.show("Topping saved");
                }
            } catch (Exception exception) {
                Notification.show(exception.getMessage());
            }
        });

        toppingId.addKeyDownListener(Key.ENTER, event -> {//read method
            try {
                TextField source = (TextField) event.getSource();
                String enteredValue = source.getValue();

                if (!enteredValue.isEmpty()) {
                    Integer enteredToppingId = Integer.valueOf(enteredValue);

                    // Call the readToppingId method to fetch topping data
                    Topping toppingData = readToppingId(enteredToppingId);

                    // Update the other text fields with the retrieved data
                    if (toppingData != null) {
                        updateToppingFields(toppingData);
                        toppingId.setEnabled(false);
                        Notification.show("Topping Id read successfully");
                    } else {
                        // Handle the case where the entered toppingId does not exist
                        Notification.show("Topping with ID " + enteredToppingId + " not found");
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
                    Topping updatedTopping = updateSetToppingValues();
                    updateTopping(updatedTopping);

                    Notification.show("Topping updated successfully");
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during the update: " + ex.getMessage());
            }
        });

        deleteButton.addClickListener(e ->{//use for delete method
            try {
                String toppingIdValue = toppingId.getValue();
                if (!toppingIdValue.isEmpty()) {
                    Integer id = Integer.valueOf(toppingIdValue);
                    deleteToppingId(id);
                    Notification.show("Topping deleted successfully");
                    clearFormFields();
                } else {
                    Notification.show("Please enter a topping ID.");
                }
            } catch (NumberFormatException ex) {
                Notification.show("Please enter a valid topping ID as a number.");
            } catch (Exception ex) {
                Notification.show("An error occurred during the delete: " + ex.getMessage());
            }
        });

        viewAllButton.addClickListener(event -> {//use for getAll method
            try {
                ArrayList<Topping> toppings = getAll.getAllToppings();
                viewContainer.removeAll();
                toppings.forEach(topping -> {
                    viewContainer.add(createToppingSpan(topping));
                });
            } catch (Exception e) {
                Notification.show("Failed to retrieve toppings. Please try again later." + e.getMessage());
            }
        });

        resetButton.addClickListener(event -> {//use for resetting the text fields
            try {
                clearFormFields();
                viewContainer.removeAll();//this is here to not display the table format when the getAll button is pressed
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

    public void createTopping(Topping topping) {//use for create method
        ToppingApi toppingApi = new ToppingApi();
        toppingApi.createTopping(topping);
    }

    public Topping readToppingId(Integer toppingId) {//use for read method
        ToppingApi readToppingApi = new ToppingApi();
        return readToppingApi.readTopping(toppingId);
    }

    public void updateTopping(Topping topping) {//use for update method
        ToppingApi updateToppingApi = new ToppingApi();
        updateToppingApi.updateTopping(topping);
    }

    public void deleteToppingId(Integer toppingId){//use for delete method
        ToppingApi deleteToppingId = new ToppingApi();
        deleteToppingId.deleteTopping(toppingId);
    }

    public Topping setToppingValues() {//use for create method

        String toppingNameValue = name.getValue();
        String descriptionValue = description.getValue();
        int quantityValue = Integer.parseInt(quantity.getValue());
        double priceValue = Double.parseDouble(price.getValue());

        Topping getToppingData = ToppingFactory.buildTopping(toppingNameValue, descriptionValue, quantityValue, priceValue);

        return getToppingData;
    }

    public void updateToppingFields(Topping topping) {//this is not an update method, this is for the read method
        name.setValue(topping.getName());
        description.setValue(topping.getDescription());
        quantity.setValue(String.valueOf(topping.getQuantity()));
        price.setValue(String.valueOf(topping.getPrice()));
    }

    public Topping updateSetToppingValues() {//use for update method
        Integer toppingIdValue = Integer.valueOf(toppingId.getValue());
        String nameValue = name.getValue();
        String descriptionValue = description.getValue();
        int quantityValue = Integer.parseInt(quantity.getValue());
        double priceValue = Double.parseDouble(price.getValue());

        Topping updateToppingData = ToppingFactory.createTopping(toppingIdValue, nameValue, descriptionValue, quantityValue, priceValue);

        return updateToppingData;
    }

    public boolean checkErrors() {//checks errors
        String toppingNameValue = name.getValue();
        String descriptionValue = description.getValue();
        String quantityValue = quantity.getValue();
        String priceValue = price.getValue();

        if (toppingNameValue.isEmpty() || descriptionValue.isEmpty() || quantityValue.isEmpty() || priceValue.isEmpty()) {
            Notification.show("Please enter in all the fields.");
            return true;
        }

        if (!toppingNameValue.matches("[a-zA-Z ]+") || !descriptionValue.matches("[a-zA-Z ]+")) {
            Notification.show("Invalid input. please only enter letters.");
            return true;
        }

        int quantityInt = Integer.parseInt(quantityValue);
        int priceInt = Integer.parseInt(priceValue);

        if (quantityInt <= 0) {
            Notification.show("Invalid input, quantity must contain numbers.");
            return true;
        }

        if (priceInt <= 0) {
            Notification.show("Invalid input, price must contain numbers.");
            return true;
        }

        return false;
    }

    private Div createToppingSpan(Topping topping) {
        Div outerDiv = new Div();
        outerDiv.getStyle().set("display", "flex");
        outerDiv.getStyle().set("justify-content", "center");
        outerDiv.getStyle().set("margin-top", "15px");
        outerDiv.getStyle().set("margin-right", "300px");

        Div innerDiv = new Div();
        innerDiv.getStyle().set("display", "flex");
        innerDiv.getStyle().set("flex-direction", "row");
        innerDiv.getStyle().set("justify-content", "space-between");

        createDataField(innerDiv, "Topping ID ", String.valueOf(topping.getToppingId()));
        createDataField(innerDiv, "Name ", topping.getName());
        createDataField(innerDiv, "Description ", topping.getDescription());
        createDataField(innerDiv, "Quantity ", String.valueOf(topping.getQuantity()));
        createDataField(innerDiv, "Price ", String.valueOf(topping.getPrice()));

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
        toppingId.clear();
        name.clear();
        description.clear();
        quantity.clear();
        price.clear();
        toppingId.setEnabled(true);
    }

}
