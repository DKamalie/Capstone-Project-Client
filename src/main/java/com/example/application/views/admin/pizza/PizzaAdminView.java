package com.example.application.views.admin.pizza;

/*
PizzaAdminView.java
Author: Tamryn Lisa Lewin (219211981)
Date: 15 October 2023
Last update: 18 October 2023
 */

import com.example.application.api.PizzaApi;
import com.example.application.domain.Base;
import com.example.application.domain.Pizza;
import com.example.application.domain.Pizzeria;
import com.example.application.factory.BaseFactory;
import com.example.application.factory.PizzaFactory;
import com.example.application.factory.PizzeriaFactory;
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
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Set;

@PageTitle("PizzaAdmin")
@Route(value = "pizzaAdmin", layout = MainLayout.class)
public class PizzaAdminView extends VerticalLayout {

    private Html heading;
    private TextField txtPizzaId, txtBaseId, txtName, txtDescription, txtSize, txtPrice, txtPizzeriaId;
    private Button btnSave, btnUpdate, btnDelete, btnViewAll, btnReset;
    RadioButtonGroup<Boolean> rdoVegetarianOrNot;
    private Div viewContainer;
    private boolean isEditing = false;

    PizzaApi pizzaApi = new PizzaApi();
//    private PizzaApi pizzaAPI;

    public PizzaAdminView() {

        heading = new Html("<H1>Pizzas</H1>");

        txtPizzaId = new TextField("Pizza Id: ");
        txtBaseId = new TextField("Base Id: ");
        txtName = new TextField("Name: ");
        txtDescription = new TextField("Description: ");
        txtSize = new TextField("Size:");
        txtPrice = new TextField("Price: ");
        txtPizzeriaId = new TextField("Pizzeria Id: ");

        btnSave = new Button("Save");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        btnViewAll = new Button("View all");
        btnReset = new Button("Reset");

        rdoVegetarianOrNot = new RadioButtonGroup<>();


        VerticalLayout inputContainer = new VerticalLayout(txtPizzaId, txtBaseId, txtName, txtDescription, txtSize, rdoVegetarianOrNot, txtPrice, txtPizzeriaId, btnSave, btnUpdate, btnDelete, btnViewAll, btnReset);
        inputContainer.setAlignItems(Alignment.BASELINE);

        viewContainer = new Div();

        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        centeringLayout.setSizeFull();

        centeringLayout.add(viewContainer);

        Div dataContainer = new Div(viewContainer);

        Div dataMarginContainer = new Div(dataContainer);
        dataMarginContainer.getStyle().set("margin-left", "200px");

        HorizontalLayout mainContainer = new HorizontalLayout(inputContainer, dataMarginContainer);
        mainContainer.setDefaultVerticalComponentAlignment(Alignment.CENTER);


//      Button events
        btnSave.addClickListener(e -> {//this is for creating a pizza and saving it to the database
            try {
                boolean hasErrors = checkErrors();

                if (!hasErrors) {
                    Pizza pizzaSave = setPizzaValues();
                    createPizza(pizzaSave);
                    Notification.show("Pizza saved");
                }
            } catch (Exception exception) {
                Notification.show(exception.getMessage());
            }
        });


        txtPizzaId.addKeyDownListener(Key.ENTER, event -> {//read method
            try {
                TextField source = (TextField) event.getSource();
                String enteredValue = source.getValue();

                if (!enteredValue.isEmpty()) {
                    Integer enteredPizzaId = Integer.valueOf(enteredValue);

                    Pizza pizzaData = readPizzaId(enteredPizzaId);

                    // Update the other text fields with the retrieved data
                    if (pizzaData != null) {
                        updatePizzaFields(pizzaData);
                        txtPizzaId.setEnabled(false);
                        txtBaseId.setEnabled(false);
                        txtPizzeriaId.setEnabled(false);
                        Notification.show("Pizza Id read successfully");
                    } else {
                        // Handle the case where the entered pizzaId does not exist
                        Notification.show("Pizza with Id " + enteredPizzaId + " not found");
                    }
                }
            } catch (NumberFormatException e) {
                // Handle the case where the entered value is not a valid integer
                Notification.show("Please enter a valid pizza ID as a number.");
            } catch (Exception e) {
                // Handle any other exceptions that may occur
                Notification.show("An error occurred: " + e.getMessage());
            }
        });


        btnUpdate.addClickListener(e -> {//use for update method
            try {
                System.out.println(isEditing);

                if (isEditing == false) {
                    Pizza updatedPizza = updateSetPizzaValues();
                    updatePizza(updatedPizza);

                    Notification.show("Pizza updated successfully");
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during the update: " + ex.getMessage());
            }
        });


        btnDelete.addClickListener(e ->{//use for delete method
            try {
                String pizzaIdValue = txtPizzaId.getValue();
                if (!pizzaIdValue.isEmpty()) {
                    Integer id = Integer.valueOf(pizzaIdValue);
                    deletePizzaId(id);
                    Notification.show("Pizza deleted successfully");
                    clearFormFields();
                } else {
                    Notification.show("Please enter a pizza ID.");
                }
            } catch (NumberFormatException ex) {
                Notification.show("Please enter a valid pizza ID as a number.");
            } catch (Exception ex) {
                Notification.show("An error occurred during the delete: " + ex.getMessage());
            }
        });


        btnViewAll.addClickListener(event -> {//use for getAll method
            try {
                Set<Pizza> pizzas = pizzaApi.getAllPizza();

                viewContainer.removeAll();

                pizzas.forEach(pizza -> {
                    viewContainer.add(createPizzaSpan(pizza));
                });
            } catch (Exception e) {

                Notification.show("Failed to retrieve pizzas. Please try again later." + e.getMessage());
            }
        });


        btnReset.addClickListener(event -> {//use for getAll method
            try {
                clearFormFields();
                viewContainer.removeAll();
            } catch (Exception e) {
                Notification.show("Failed to clear the fields." + e.getMessage());
            }
        });


//      Styling components
        txtPizzaId.setPlaceholder("Enter a valid pizza Id");
        txtPizzaId.setWidth("300px");

        txtBaseId.setPlaceholder("");
        txtBaseId.setWidth("300px");

        txtName.setPlaceholder("Enter a name");
        txtName.setWidth("300px");

        txtDescription.setPlaceholder("Enter a description");
        txtDescription.setWidth("300px");

        txtSize.setPlaceholder("Enter a size");
        txtSize.setWidth("300px");

        rdoVegetarianOrNot.setLabel("Vegetarian");
        rdoVegetarianOrNot.setItems(true, false);

        txtPrice.setPlaceholder("Enter a price");
        txtPrice.setWidth("300px");

        txtPizzeriaId.setPlaceholder("");
        txtPizzeriaId.setWidth("300px");


        Style btnSaveStyle = btnSave.getStyle();
        btnSaveStyle.set("color", "white");
        btnSaveStyle.set("background-color", "#000000");
        btnSaveStyle.set("font-family", "Georgia, serif");
        btnSaveStyle.set("font-size", "16px");
        btnSaveStyle.set("font-weight", "bold");
        btnSaveStyle.set("width", "300px");
        btnSaveStyle.set("border-radius", "22px");
        btnSaveStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style btnUpdateStyle = btnUpdate.getStyle();
        btnUpdateStyle.set("color", "white");
        btnUpdateStyle.set("background-color", "#000000");
        btnUpdateStyle.set("font-family", "Georgia, serif");
        btnUpdateStyle.set("font-size", "16px");
        btnUpdateStyle.set("font-weight", "bold");
        btnUpdateStyle.set("width", "300px");
        btnUpdateStyle.set("border-radius", "22px");
        btnUpdateStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style btnDeleteStyle = btnDelete.getStyle();
        btnDeleteStyle.set("color", "white");
        btnDeleteStyle.set("background-color", "#000000");
        btnDeleteStyle.set("font-family", "Georgia, serif");
        btnDeleteStyle.set("font-size", "16px");
        btnDeleteStyle.set("font-weight", "bold");
        btnDeleteStyle.set("width", "300px");
        btnDeleteStyle.set("border-radius", "22px");
        btnDeleteStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style btnViewAllStyle = btnViewAll.getStyle();
        btnViewAllStyle.set("color", "white");
        btnViewAllStyle.set("background-color", "#000000");
        btnViewAllStyle.set("font-family", "Georgia, serif");
        btnViewAllStyle.set("font-size", "16px");
        btnViewAllStyle.set("font-weight", "bold");
        btnViewAllStyle.set("width", "300px");
        btnViewAllStyle.set("border-radius", "22px");
        btnViewAllStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style btnResetStyle = btnReset.getStyle();
        btnResetStyle.set("color", "white");
        btnResetStyle.set("background-color", "#000000");
        btnResetStyle.set("font-family", "Georgia, serif");
        btnResetStyle.set("font-size", "16px");
        btnResetStyle.set("font-weight", "bold");
        btnResetStyle.set("width", "300px");
        btnResetStyle.set("border-radius", "22px");
        btnResetStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");


//      Add components to layout
        add(mainContainer);
    }


    public void createPizza(Pizza pizza) {
        pizzaApi.createPizza(pizza);
    }

    public Pizza readPizzaId(Integer pizzaId) {
        return pizzaApi.readPizza(pizzaId);
    }

    public void updatePizza(Pizza pizza) {
        pizzaApi.updatePizza(pizza);
    }

    public void deletePizzaId(Integer pizzaId){
        pizzaApi.deletePizza(pizzaId);
    }
    Base base = BaseFactory.buildBase( Base.BaseCrust.CRUSTY, Base.BaseThickness.THICK, Base.BaseTexture.CRISPY, 22);
    Pizzeria pizzeria = PizzeriaFactory.buildPizzaria("Hill Crest", "300 Long St, Cape Town City Centre, 8000");

    public void updatePizzaFields(Pizza pizza) {//this is not an update method, this is for the read method
        txtBaseId.setValue(String.valueOf(pizza.getBase().getBaseId()));
        txtName.setValue(pizza.getName());
        txtDescription.setValue(pizza.getDescription());
        txtSize.setValue(String.valueOf(pizza.getSize()));
        rdoVegetarianOrNot.setValue(pizza.isVegetarianOrNot());
        txtPrice.setValue(String.valueOf(pizza.getPrice()));
        txtPizzeriaId.setValue(String.valueOf(pizza.getPizzeria().getPizzeriaID()));
    }

    public Pizza setPizzaValues() {//use for create method
        String name = txtName.getValue();
        String description = txtDescription.getValue();
        String size = txtSize.getValue();
        Boolean vegetarianOrNot = rdoVegetarianOrNot.getValue();
        Double price = Double.valueOf(txtPrice.getValue());


        Pizza getPizzaData = PizzaFactory.createPizza(base, name, description, Pizza.Size.valueOf(size), vegetarianOrNot, price, pizzeria);

        return getPizzaData;
    }


    public Pizza updateSetPizzaValues() {//use for update method

        Integer pizzaIdValue = Integer.valueOf(txtPizzaId.getValue());
        String nameValue = txtName.getValue();
        String descriptionValue = txtDescription.getValue();
        Pizza.Size sizeValue = Pizza.Size.valueOf(txtSize.getValue());
        boolean vegetarianOrNotValueValue = rdoVegetarianOrNot.getValue();
        Double priceValue = Double.valueOf(txtPrice.getValue());

        Pizza updatePizzaData = PizzaFactory.createPizza(
                pizzaIdValue,
                base,
                nameValue,
                descriptionValue,
                sizeValue,
                vegetarianOrNotValueValue,
                priceValue,
                pizzeria);

        return updatePizzaData;
    }

    public boolean checkErrors() {

        String name = txtName.getValue();
        String description = txtDescription.getValue();
        String size = txtSize.getValue();
        String price = txtPrice.getValue();

        Boolean selectedOption = rdoVegetarianOrNot.getValue();
        if (selectedOption != null) {
            if (selectedOption.equals(true)) {
                Notification.show("You selected the 'true' option.");
            } else if (selectedOption.equals(false)) {
                Notification.show("You selected the 'false' option.");
            }
        } else {
            Notification.show("Please select an option.");
        }

        if (name.isEmpty() || description.isEmpty() || size.isEmpty() ||  price.isEmpty()) {
            Notification.show("Please enter in all the fields.");
            return true;
        }

        if (!name.matches("[a-zA-Z ]+") || !description.matches("[a-zA-Z ]+") || !size.matches("[a-zA-Z ]+")) {
            Notification.show("Invalid input. please only enter letters.");
            return true;
        }

        double priceValueDouble = Double.parseDouble(txtPrice.getValue());

        if (priceValueDouble <= 0) {
            Notification.show("Invalid input, price must contain numbers.");
            return true;
        }

        if (!price.matches("^[0-9]+$")) {
            Notification.show("Invalid input, please only enter in numbers.");
            return true;
        }
        return false;
    }

    private Div createPizzaSpan(Pizza pizza) {
        Div outerDiv = new Div();
        outerDiv.getStyle().set("display", "flex");
        outerDiv.getStyle().set("justify-content", "center");
        outerDiv.getStyle().set("margin-top", "15px");
        outerDiv.getStyle().set("margin-right", "300px");

        Div innerDiv = new Div();
        innerDiv.getStyle().set("display", "flex");
        innerDiv.getStyle().set("flex-direction", "row");
        innerDiv.getStyle().set("justify-content", "space-between");

        createDataField(innerDiv, "Pizza Id ", String.valueOf(pizza.getPizzaId()));
        createDataField(innerDiv, "Base Id ", String.valueOf(pizza.getBase().getBaseId()));
        createDataField(innerDiv, "Name ", pizza.getName());
        createDataField(innerDiv, "Description ", pizza.getDescription());
        createDataField(innerDiv, "Size ", String.valueOf(pizza.getSize()));
        createDataField(innerDiv, "Vegetarian ", String.valueOf(pizza.isVegetarianOrNot()));
        createDataField(innerDiv, "Price ", String.valueOf(pizza.getPrice()));
        createDataField(innerDiv, "Pizzeria ", String.valueOf(pizza.getPizzeria().getPizzeriaID()));

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

    private void clearFormFields() {
        txtPizzaId.clear();
        txtBaseId.clear();
        txtPizzaId.setEnabled(true);
        txtName.clear();
        txtDescription.clear();
        txtSize.clear();
        rdoVegetarianOrNot.clear();
        txtPrice.clear();
        txtPizzeriaId.clear();
    }
}
