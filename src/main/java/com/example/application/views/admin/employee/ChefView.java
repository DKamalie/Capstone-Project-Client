package com.example.application.views.admin.employee;

import com.example.application.api.ChefApi;
import com.example.application.api.EmployeeApi;
import com.example.application.domain.Chef;
import com.example.application.domain.Pizzeria;
import com.example.application.factory.ChefFactory;
import com.example.application.factory.PizzeriaFactory;
import com.example.application.views.MainLayout;
import com.example.application.views.admindashboard.AdminDashboard;
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

@PageTitle("Chef")
@Route(value = "chef", layout = MainLayout.class)
public class ChefView extends VerticalLayout {

    private TextField employeeId;
    private TextField name;
    private TextField surname;
    private TextField phoneNumber;
    private TextField email;
    private TextField title;
    private TextField culinaryExperience;
    private TextField pizzeriaId;
    private Button saveButton;
    private Button updateButton;
    private Button deleteButton;
    private Button viewAllButton;
    private Button resetButton;
    private Div viewContainer;
    private boolean isEditing = false;

    EmployeeApi getALL = new EmployeeApi();

    public ChefView(){
        employeeId = new TextField("Employee Id: ");
        employeeId.setWidth("300px");
        employeeId.setPlaceholder("Enter in the employee Id");

        name = new TextField("Name: ");
        name.setWidth("300px");
        name.setPlaceholder("Enter in the name");

        surname = new TextField("Surname: ");
        surname.setWidth("300px");
        surname.setPlaceholder("Enter in the surname");

        phoneNumber = new TextField("Phone number: ");
        phoneNumber.setWidth("300px");
        phoneNumber.setPlaceholder("Enter in the phone number");

        email = new TextField("Email: ");
        email.setWidth("300px");
        email.setPlaceholder("Enter in the email");

        title = new TextField("Title: ");
        title.setWidth("300px");
        title.setPlaceholder("Enter in the title");

        culinaryExperience = new TextField("Culinary experience: ");
        culinaryExperience.setWidth("300px");
        culinaryExperience.setPlaceholder("Enter in the culinary experience");

        pizzeriaId = new TextField("Pizzeria Id: ");
        pizzeriaId.setWidth("300px");
        pizzeriaId.setPlaceholder("");
        pizzeriaId.setEnabled(false);

        saveButton = new Button("Save");
        saveButton.setWidth("300px");

        updateButton = new Button("Update");
        updateButton.setWidth("300px");
        updateButton.setEnabled(true);

        deleteButton = new Button("Delete");
        deleteButton.setWidth("300px");

        viewAllButton = new Button("View all chefs");
        viewAllButton.setWidth("300px");

        resetButton = new Button("Reset");
        resetButton.setWidth("300px");

        VerticalLayout inputContainer = new VerticalLayout(employeeId, name, surname, phoneNumber, email,title, culinaryExperience, pizzeriaId, saveButton, updateButton, deleteButton, viewAllButton, resetButton);
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


        saveButton.addClickListener(e -> {//this is for creating a chef and saving it to the database
            try {
                boolean hasErrors = checkErrors();

                if (!hasErrors) {
                    Chef chefSave = setChefValues();
                    createChef(chefSave);
                    Notification.show("Chef saved");
                }
            } catch (Exception exception) {
                Notification.show(exception.getMessage());
            }
        });

        employeeId.addKeyDownListener(Key.ENTER, event -> {//read method
            try {
                TextField source = (TextField) event.getSource();
                String enteredValue = source.getValue();

                if (!enteredValue.isEmpty()) {
                    Integer enteredEmployeeId = Integer.valueOf(enteredValue);

                    // Call the readChefId method to fetch chef data
                    Chef employeeData = readEmployeeId(enteredEmployeeId);

                    // Update the other text fields with the retrieved data
                    if (employeeData != null) {
                        updateChefFields(employeeData);
                        employeeId.setEnabled(false);
                        pizzeriaId.setEnabled(false);
                        Notification.show("Employee Id read successfully");
                    } else {
                        // Handle the case where the entered chefId does not exist
                        Notification.show("Employee with ID " + enteredEmployeeId + " not found");
                    }
                }
            } catch (NumberFormatException e) {
                // Handle the case where the entered value is not a valid integer
                Notification.show("Please enter a valid employee ID as a number.");
            } catch (Exception e) {
                // Handle any other exceptions that may occur
                Notification.show("An error occurred: " + e.getMessage());
            }
        });

        updateButton.addClickListener(e -> {//use for update method
            try {
                System.out.println(isEditing);

                if (isEditing == false) {
                    Chef updatedChef = updateSetChefValues();
                    updateChef(updatedChef);

                    Notification.show("Chef updated successfully");
                }
            } catch (Exception ex) {
                Notification.show("An error occurred during the update: " + ex.getMessage());
            }
        });

        deleteButton.addClickListener(e ->{//use for delete method
            try {
                String employeeIdValue = employeeId.getValue();
                if (!employeeIdValue.isEmpty()) {
                    Integer id = Integer.valueOf(employeeIdValue);
                    deleteEmployeeId(id);
                    Notification.show("Chef deleted successfully");
                    clearFormFields();
                } else {
                    Notification.show("Please enter a employee ID.");
                }
            } catch (NumberFormatException ex) {
                Notification.show("Please enter a valid employee ID as a number.");
            } catch (Exception ex) {
                Notification.show("An error occurred during the delete: " + ex.getMessage());
            }
        });

        viewAllButton.addClickListener(event -> {//use for getAll method
            try {
                Set<Chef> chefs = getALL.getAllChef();
                viewContainer.removeAll();
                chefs.forEach(chef -> {
                    viewContainer.add(createChefSpan(chef));
                });
            } catch (Exception e) {
                Notification.show("Failed to retrieve chefs. Please try again later." + e.getMessage());
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

    public void createChef(Chef chef) {//use for create method
        ChefApi chefApi = new ChefApi();
        chefApi.createChef(chef);
    }

    public Chef readEmployeeId(Integer employeeId) {//use for read method
        ChefApi readEmployeeId = new ChefApi();
        return readEmployeeId.readChef(employeeId);
    }

    public void updateChef(Chef chef) {//use for the update method
        ChefApi updateChef = new ChefApi();
        updateChef.updateChef(chef);
    }

    public void deleteEmployeeId(Integer employeeId){//use for delete method
        ChefApi deleteEmployeeId = new ChefApi();
        deleteEmployeeId.deleteChef(employeeId);
    }

    Pizzeria pizzeria = PizzeriaFactory.buildPizzaria("Hill Crest", "300 Long St, Cape Town City Centre, 8000");

    public void updateChefFields(Chef chef) {//this is not an update method, this is for the read method
        name.setValue(chef.getName());
        surname.setValue(chef.getSurname());
        phoneNumber.setValue(chef.getPhoneNumber());
        email.setValue(chef.getEmail());
        title.setValue(chef.getTitle());
        culinaryExperience.setValue(chef.getCulinaryExperience());
        pizzeriaId.setValue(String.valueOf(chef.getPizzeria().getPizzeriaID()));
    }


    public Chef updateSetChefValues() {//use for update method
        Integer employeeIdValue = Integer.valueOf(employeeId.getValue());
        String nameValue = name.getValue();
        String surnameValue = surname.getValue();
        String phoneNumberValue = phoneNumber.getValue();
        String emailValue = email.getValue();
        String titleValue = title.getValue();
        String culinaryExperienceValue = culinaryExperience.getValue();

        Chef updateChefData = ChefFactory.createChef(employeeIdValue, nameValue, surnameValue, phoneNumberValue, emailValue, titleValue, culinaryExperienceValue, pizzeria);

        return updateChefData;
    }



    public Chef setChefValues() {//use for create method
        String nameValue = name.getValue();
        String surnameValue = surname.getValue();
        String phoneNumberValue = phoneNumber.getValue();
        String emailValue = email.getValue();
        String titleValue = title.getValue();
        String culinaryExperienceValue = culinaryExperience.getValue();

        Chef getChefData = ChefFactory.buildChef(nameValue, surnameValue, phoneNumberValue, emailValue, titleValue, culinaryExperienceValue, pizzeria);

        return getChefData;
    }

    public boolean isValidEmail(String email) {//email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return email.matches(emailRegex);
    }

    public boolean checkErrors() {//checks errors
        String nameValue = name.getValue();
        String surnameValue = surname.getValue();
        String phoneNumberValue = phoneNumber.getValue();
        String emailValue = email.getValue();
        String titleValue = title.getValue();
        String culinaryExperienceValue = culinaryExperience.getValue();

        if (nameValue.isEmpty() || surnameValue.isEmpty() || phoneNumberValue.isEmpty() || emailValue.isEmpty() || titleValue.isEmpty() || culinaryExperienceValue.isEmpty()) {
            Notification.show("Please enter in all the fields.");
            return true;
        }

        if (!nameValue.matches("[a-zA-Z ]+") || !surnameValue.matches("[a-zA-Z ]+") || !titleValue.matches("[a-zA-Z ]+") || !culinaryExperienceValue.matches("[a-zA-Z ]+")) {
            Notification.show("Invalid input. please only enter letters.");
            return true;
        }

        if (!phoneNumberValue.matches("^[0-9]+$")) {
            Notification.show("Invalid input, please only enter in numbers.");
            return true;
        }

        if(!(phoneNumberValue.length() == 10)){
            Notification.show("Invalid input, phone number must contain 10 digits");
            return true;
        }

        if (!isValidEmail(emailValue)){
            Notification.show("Invalid email, please try again");
            return true;
        }
        return false;
    }

    private Div createChefSpan(Chef chef) {
        Div outerDiv = new Div();
        outerDiv.getStyle().set("display", "flex");
        outerDiv.getStyle().set("justify-content", "center");
        outerDiv.getStyle().set("margin-top", "15px");
        outerDiv.getStyle().set("margin-right", "300px");

        Div innerDiv = new Div();
        innerDiv.getStyle().set("display", "flex");
        innerDiv.getStyle().set("flex-direction", "row");
        innerDiv.getStyle().set("justify-content", "space-between");

        createDataField(innerDiv, "Employee ID ", String.valueOf(chef.getEmpId()));
        createDataField(innerDiv, "Name ", chef.getName());
        createDataField(innerDiv, "Surname ", chef.getSurname());
        createDataField(innerDiv, "Phone number ", chef.getPhoneNumber());
        createDataField(innerDiv, "Email ", chef.getEmail());

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
        employeeId.clear();
        name.clear();
        surname.clear();
        phoneNumber.clear();
        email.clear();
        title.clear();
        culinaryExperience.clear();
        pizzeriaId.clear();
        employeeId.setEnabled(true);
    }
}
