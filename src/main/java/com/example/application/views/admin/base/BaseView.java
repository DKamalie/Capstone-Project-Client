package com.example.application.views.admin.base;

import com.example.application.api.BaseApi;
import com.example.application.domain.Base;
import com.example.application.factory.BaseFactory;
import com.example.application.views.MainLayout;
import com.example.application.views.admindashboard.AdminDashboard;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

@PageTitle("Base")
@Route(value = "base", layout = MainLayout.class)
public class BaseView extends VerticalLayout {

    private TextField baseId;
    private TextField crust;
    private TextField thickness;
    private TextField texture;
    private TextField price;
    private Button saveButton;
    private Button updateButton;
    private Button deleteButton;
    private Button viewAllButton;
    private Button resetButton;
    private Div viewContainer;
    private boolean isEditing = false;

    BaseApi getAll = new BaseApi();

    public BaseView() {
        baseId = new TextField("Base Id: ");
        baseId.setWidth("300px");
        baseId.setPlaceholder("Enter in the base Id");

        crust = new TextField("Crust: ");
        crust.setWidth("300px");
        crust.setPlaceholder("Enter in the crust");

        thickness = new TextField("Thickness: ");
        thickness.setWidth("300px");
        thickness.setPlaceholder("Enter in the thickness");

        texture = new TextField("Texture: ");
        texture.setWidth("300px");
        texture.setPlaceholder("Enter in the texture");

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

        viewAllButton = new Button("View all bases");
        viewAllButton.setWidth("300px");

        resetButton = new Button("Reset");
        resetButton.setWidth("300px");

        VerticalLayout inputContainer = new VerticalLayout(baseId, crust, thickness, texture, price, saveButton, updateButton, deleteButton, viewAllButton, resetButton);
        inputContainer.setAlignItems(FlexComponent.Alignment.BASELINE);

        viewContainer = new Div();

        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        centeringLayout.setSizeFull();

        centeringLayout.add(viewContainer);

        Div dataContainer = new Div(viewContainer);

        Div dataMarginContainer = new Div(dataContainer);
        dataMarginContainer.getStyle().set("margin-left", "200px");

        HorizontalLayout mainContainer = new HorizontalLayout(inputContainer, dataMarginContainer);
        mainContainer.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        saveButton.addClickListener(e -> {//this is for creating a base and saving it to the database
            try {
                boolean hasErrors = checkErrors();

                if (!hasErrors) {
                    Base baseSave = setBaseValues();
                    createBase(baseSave);
                    Notification.show("Base saved");
                }
            } catch (Exception exception) {
                Notification.show(exception.getMessage());
            }
        });

        baseId.addKeyDownListener(Key.ENTER, event -> {//read method
            try {
                TextField source = (TextField) event.getSource();
                String enteredValue = source.getValue();

                if (!enteredValue.isEmpty()) {
                    Integer enteredBaseId = Integer.valueOf(enteredValue);

                    // Call the readBaseId method to fetch base data
                    Base baseData = readBaseId(enteredBaseId);

                    // Update the other text fields with the retrieved data
                    if (baseData != null) {
                        updateBaseFields(baseData);
                        baseId.setEnabled(false);
                        Notification.show("Base Id read successfully");
                    } else {
                        // Handle the case where the entered baseId does not exist
                        Notification.show("Vehicle with ID " + enteredBaseId + " not found");
                    }
                }
            } catch (NumberFormatException e) {
                // Handle the case where the entered value is not a valid integer
                Notification.show("Please enter a valid base ID as a number.");
            } catch (Exception e) {
                // Handle any other exceptions that may occur
                Notification.show("An error occurred: " + e.getMessage());
            }
        });

        updateButton.addClickListener(e -> {//use for update method
            try {
                System.out.println(isEditing);

                if (isEditing == false) {
                    Base updatedBase = updateSetBaseValues();
                    updateBase(updatedBase);

                    Notification.show("Base updated successfully");
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during the update: " + ex.getMessage());
            }
        });

        deleteButton.addClickListener(e -> {//use for delete method
            try {
                String baseIdValue = baseId.getValue();
                if (!baseIdValue.isEmpty()) {
                    Integer id = Integer.valueOf(baseIdValue);
                    deleteBaseId(id);
                    Notification.show("Base deleted successfully");
                    clearFormFields();
                } else {
                    Notification.show("Please enter a base ID.");
                }
            } catch (NumberFormatException ex) {
                Notification.show("Please enter a valid base ID as a number.");
            } catch (Exception ex) {
                Notification.show("An error occurred during the delete: " + ex.getMessage());
            }
        });

        viewAllButton.addClickListener(event -> {//use for getAll method
            try {
                ArrayList<Base> bases = getAll.getAllBase();

                viewContainer.removeAll();

                bases.forEach(base -> {
                    viewContainer.add(createBaseSpan(base));
                });
            } catch (Exception e) {

                Notification.show("Failed to retrieve bases. Please try again later." + e.getMessage());
            }
        });

        resetButton.addClickListener(event -> {//use for getAll method
            try {
                clearFormFields();

                viewContainer.removeAll();

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

        Button backButton = new Button("< Back");
        backButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(AdminDashboard.class)));


        backButton.getStyle().set("color", "white");
        backButton.getStyle().set("background-color", "#000000");
        backButton.getStyle().set("font-family", "Arial");
        backButton.getStyle().set("font-size", "16px");
        backButton.getStyle().set("font-weight", "bold");
        backButton.getStyle().set("border-radius", "17px");
        backButton.getStyle().set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");


        HorizontalLayout buttonLayout = new HorizontalLayout(backButton);
        buttonLayout.setVerticalComponentAlignment(Alignment.START, backButton);
        add(buttonLayout);

        setMargin(true);
        add(mainContainer);
    }

    public void createBase(Base base) {//use for create method
        BaseApi baseApi = new BaseApi();
        baseApi.createBase(base);
    }

    public Base readBaseId(Integer baseId) {//use for read method
        BaseApi readBaseId = new BaseApi();
        return readBaseId.readBase(baseId);
    }

    public void updateBase(Base base) {//use for the update method
        BaseApi updateBase = new BaseApi();
        updateBase.updateBase(base);
    }

    public void deleteBaseId(Integer baseId){//use for delete method
        BaseApi deleteBaseId = new BaseApi();
        deleteBaseId.deleteBase(baseId);
    }

    public void updateBaseFields(Base base) {//this is not an update method, this is for the read method
        crust.setValue(String.valueOf(base.getCrust()));
        thickness.setValue(String.valueOf(base.getThickness()));
        texture.setValue(String.valueOf(base.getTexture()));
        price.setValue(String.valueOf(base.getPrice()));
    }

    public Base setBaseValues() {//use for create method

        Base.BaseCrust crustValue = Base.BaseCrust.valueOf(crust.getValue());
        Base.BaseThickness thicknessValue = Base.BaseThickness.valueOf(thickness.getValue());
        Base.BaseTexture textureValue = Base.BaseTexture.valueOf(texture.getValue());
        double priceValue = Double.parseDouble(price.getValue());

        Base getBaseData = BaseFactory.buildBase(crustValue, thicknessValue, textureValue, priceValue);

        return getBaseData;
    }

    public Base updateSetBaseValues() {//use for update method

        Integer baseIdValue = Integer.valueOf(baseId.getValue());
        Base.BaseCrust crustValue = Base.BaseCrust.valueOf(crust.getValue());
        Base.BaseThickness thicknessValue = Base.BaseThickness.valueOf(thickness.getValue());
        Base.BaseTexture textureValue = Base.BaseTexture.valueOf(texture.getValue());
        double priceValue = Double.parseDouble(price.getValue());

        Base updateBaseData = BaseFactory.createBase(
                baseIdValue,
                crustValue,
                thicknessValue,
                textureValue,
                priceValue
                );

        return updateBaseData;
    }

    public boolean checkErrors() {//checks errors

        String crustValue = crust.getValue();
        String thicknessValue = thickness.getValue();
        String textureValue = texture.getValue();
        String priceValue = price.getValue();
        double priceValueDouble = Double.parseDouble(price.getValue());

        if (crustValue.isEmpty() || thicknessValue.isEmpty() || textureValue.isEmpty() || priceValue.isEmpty()) {
            Notification.show("Please enter in all the fields.");
            return true;
        }

        if (!crustValue.matches("[a-zA-Z ]+") || !thicknessValue.matches("[a-zA-Z ]+") || !textureValue.matches("[a-zA-Z ]+")) {
            Notification.show("Invalid input. please only enter letters.");
            return true;
        }

        if (priceValueDouble <= 0) {
            Notification.show("Invalid input, price must contain numbers.");
            return true;
        }

        return false;
    }

    private Div createBaseSpan(Base base) {
        Div outerDiv = new Div();
        outerDiv.getStyle().set("display", "flex");
        outerDiv.getStyle().set("justify-content", "center");
        outerDiv.getStyle().set("margin-top", "15px");
        outerDiv.getStyle().set("margin-right", "300px");

        Div innerDiv = new Div();
        innerDiv.getStyle().set("display", "flex");
        innerDiv.getStyle().set("flex-direction", "row");
        innerDiv.getStyle().set("justify-content", "space-between");

        createDataField(innerDiv, "Base ID ", String.valueOf(base.getBaseId()));
        createDataField(innerDiv, "Crust ", String.valueOf(base.getCrust()));
        createDataField(innerDiv, "Thickness ", String.valueOf(base.getThickness()));
        createDataField(innerDiv, "Texture ", String.valueOf(base.getTexture()));
        createDataField(innerDiv, "Price ", String.valueOf(base.getPrice()));

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
        baseId.clear();
        crust.clear();
        thickness.clear();
        texture.clear();
        price.clear();
        baseId.setEnabled(true);
    }
}
