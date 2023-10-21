package com.example.application.views.admin.employee;

import com.example.application.api.EmployeeApi;
import com.example.application.domain.*;
import com.example.application.factory.EmployeeFactory;
import com.example.application.factory.PizzeriaFactory;
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

import java.util.Set;

@PageTitle("Employee")
@Route(value = "employee", layout = MainLayout.class)
public class EmployeeView extends VerticalLayout {

    private TextField employeeId;
    private TextField name;
    private TextField surname;
    private TextField phoneNumber;
    private TextField email;
    private TextField pizzeriaId;
    private Button saveButton;
    private Button updateButton;
    private Button deleteButton;
    private Button viewAllButton;
    private Button resetButton;
    private Div viewContainer;
    private boolean isEditing = false;

    EmployeeApi getAll = new EmployeeApi();

    public EmployeeView(){
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

        viewAllButton = new Button("View all employees");
        viewAllButton.setWidth("300px");

        resetButton = new Button("Reset");
        resetButton.setWidth("300px");

        VerticalLayout inputContainer = new VerticalLayout(employeeId, name, surname, phoneNumber, email, pizzeriaId, saveButton, updateButton, deleteButton, viewAllButton, resetButton);
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


        saveButton.addClickListener(e -> {//this is for creating a employee and saving it to the database
            try {
                boolean hasErrors = checkErrors();

                if (!hasErrors) {
                    Employee employeeSave = setEmployeeValues();
                    createEmployee(employeeSave);
                    Notification.show("Employee saved");
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

                    // Call the readEmployeeId method to fetch vehicle data
                    Employee employeeData = readEmployeeId(enteredEmployeeId);

                    // Update the other text fields with the retrieved data
                    if (employeeData != null) {
                        updateEmployeeFields(employeeData);
                        employeeId.setEnabled(false);
                        pizzeriaId.setEnabled(false);
                        Notification.show("Employee Id read successfully");
                    } else {
                        // Handle the case where the entered employeeId does not exist
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
                    Employee updatedEmployee = updateSetEmployeeValues();
                    updateEmployee(updatedEmployee);

                    Notification.show("Employee updated successfully");
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
                    Notification.show("Employee deleted successfully");
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
                Set<Employee> employees = getAll.getAllEmployee();
                Set<Chef> chefs = getAll.getAllChef();
                Set<Driver> drivers = getAll.getAllDriver();

                viewContainer.removeAll();

                employees.forEach(employee -> {
                    viewContainer.add(createEmployeeSpan(employee));
                });
            } catch (Exception e) {
                Notification.show("Failed to retrieve employees. Please try again later." + e.getMessage());
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

        setMargin(true);

        add(mainContainer);
    }

    public void createEmployee(Employee employee) {//use for create method
        EmployeeApi employeeApi = new EmployeeApi();
        employeeApi.createEmployee(employee);
    }

    public Employee readEmployeeId(Integer employeeId) {//use for read method
        EmployeeApi readEmployeeId = new EmployeeApi();
        return readEmployeeId.readEmployee(employeeId);
    }

    public void updateEmployee(Employee employee) {//use for the update method
        EmployeeApi updateEmployee = new EmployeeApi();
        updateEmployee.updateEmployee(employee);
    }

    public void deleteEmployeeId(Integer employeeId){//use for delete method
        EmployeeApi deleteEmployeeId = new EmployeeApi();
        deleteEmployeeId.deleteEmployee(employeeId);
    }
    Pizzeria pizzeria = PizzeriaFactory.buildPizzaria("Hill Crest", "300 Long St, Cape Town City Centre, 8000");

    public void updateEmployeeFields(Employee employee) {//this is not an update method, this is for the read method
        name.setValue(employee.getName());
        surname.setValue(employee.getSurname());
        phoneNumber.setValue(employee.getPhoneNumber());
        email.setValue(employee.getEmail());
        pizzeriaId.setValue(String.valueOf(employee.getPizzeria().getPizzeriaID()));
    }

    public Employee setEmployeeValues() {//use for create method

        String nameValue = name.getValue();
        String surnameValue = surname.getValue();
        String phoneNumberValue = phoneNumber.getValue();
        String emailValue = email.getValue();

        Employee getEmployeeData = EmployeeFactory.buildEmployee(nameValue, surnameValue, phoneNumberValue, emailValue, pizzeria);

        return getEmployeeData;
    }

    public Employee updateSetEmployeeValues() {//use for update method
        Integer employeeIdValue = Integer.valueOf(employeeId.getValue());
        String nameValue = name.getValue();
        String surnameValue = surname.getValue();
        String phoneNumberValue = phoneNumber.getValue();
        String emailValue = email.getValue();

        Employee updateEmployeeData = EmployeeFactory.createEmployee(employeeIdValue, nameValue, surnameValue, phoneNumberValue, emailValue, pizzeria );

        return updateEmployeeData;
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

        if (nameValue.isEmpty() || surnameValue.isEmpty() || phoneNumberValue.isEmpty() || emailValue.isEmpty()) {
            Notification.show("Please enter in all the fields.");
            return true;
        }

        if (!nameValue.matches("[a-zA-Z ]+") || !surnameValue.matches("[a-zA-Z ]+")) {
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

    private void createDataField(Div container, String label, String value) {
        Span labelSpan = new Span(label);
        labelSpan.getStyle().set("font-weight", "bold");
        Span valueSpan = new Span(value);

        Div dataField = new Div(labelSpan, valueSpan);
        dataField.getStyle().set("margin-right", "40px");

        container.add(dataField);
    }


    private Div createEmployeeSpan(Employee employee) {
        Div outerDiv = new Div();
        outerDiv.getStyle().set("display", "flex");
        outerDiv.getStyle().set("justify-content", "center");
        outerDiv.getStyle().set("margin-top", "15px");
        outerDiv.getStyle().set("margin-right", "300px");

        Div innerDiv = new Div();
        innerDiv.getStyle().set("display", "flex");
        innerDiv.getStyle().set("flex-direction", "row");
        innerDiv.getStyle().set("justify-content", "space-between");

        createDataField(innerDiv, "Employee ID ", String.valueOf(employee.getEmpId()));
        createDataField(innerDiv, "Name ", employee.getName());
        createDataField(innerDiv, "Surname ", employee.getSurname());
        createDataField(innerDiv, "Phone number ", employee.getPhoneNumber());
        createDataField(innerDiv, "Email ", employee.getEmail());

        outerDiv.add(innerDiv);

        return outerDiv;
    }

    private void clearFormFields() {//clear text fields
        employeeId.clear();
        name.clear();
        surname.clear();
        phoneNumber.clear();
        email.clear();
        pizzeriaId.clear();
        employeeId.setEnabled(true);
    }
}
