package com.example.controller.employee;

import com.example.model.Employee;
import com.example.model.Restaurant;
import com.example.model.Table;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class formEmployeesController implements Initializable {

    @FXML
    private Button displayEmployeeButton;
    @FXML
    private Label displayEmployeeLabel;
    @FXML
    private Button deleteEmployeeButton;
    @FXML
    private TextField specifyNumberTableDeleteTextfield;
    @FXML
    private Button addEmployeeButton;
    @FXML
    private TextField specifyNumberTableAddTextfield;
    @FXML
    private TextField specifyNumberTableAddPositionTextfield;
    @FXML
    private TextField specifyNumberTableAddHoursTextfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Restaurant restaurant = new Restaurant();

        /**
         * Display employees
         * @param mouseEvent
         */

        displayEmployeeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String dataJson = "[]";
                List employees = new ArrayList<>();
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/employee.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JSONArray myArray = new JSONArray(dataJson);

                // The label is cleared
                displayEmployeeLabel.setText("");

                // We display the employees in the label
                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    displayEmployeeLabel.setText(displayEmployeeLabel.getText() + " name:" + myJSONObject.getString("name"));
                }
            }
        });

        /**
         * Add employee
         * @param mouseEvent
         */
        deleteEmployeeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String dataJson = "[]";
                List employees = new ArrayList<>();
                List<Employee> employeesList = restaurant.getEmployees();
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/employee.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JSONArray myArray = new JSONArray(dataJson);

                // Verify if the textfield is empty
                if (specifyNumberTableDeleteTextfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un nom d'employé.");
                    return;
                }

                // Create a variable to store the employee's name
                String tableNumberString = specifyNumberTableDeleteTextfield.getText();
                String employeeName = String.valueOf(tableNumberString);
                boolean isEmployeeFound = false;

                // We search the employee in the JSON file
                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    String name = myJSONObject.getString("name");
                    if (name.equals(employeeName)) {
                        isEmployeeFound = true;
                        myArray.remove(i);
                        break;
                    }
                }

                // If the employee is found, we delete it
                // Else, we display an error message
                // We update the JSON file
                // We update the restaurant object
                // We display a success message
                // We display an error message
                if (isEmployeeFound) {
                    // Mise à jour du fichier JSON
                    try {
                        String jsonString = myArray.toString();
                        Files.write(Paths.get("./json/employee.json"), jsonString.getBytes());
                        System.out.println("L'employé numéro " + employeeName + " a été supprimé.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Mise à jour de l'objet Restaurant
                    restaurant.removeEmployee(employeeName);
                } else {
                    System.out.println("L'employé numéro " + employeeName + " n'a pas été trouvé.");
                }
            }
        });

        /**
         * Add employee
         * @param mouseEvent
         */
        addEmployeeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // We get the employees list
                // We verify if the textfields are empty
                // We get the textfields values
                List<Employee> employeesList = restaurant.getEmployees();
                if (specifyNumberTableAddTextfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un nom d'employé.");
                    return;
                }

                if (specifyNumberTableAddPositionTextfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un poste.");
                    return;
                }

                if (specifyNumberTableAddHoursTextfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un nombre d'heures travaillées.");
                    return;
                }

                // We create a variable to store the employee's name and position and hours worked
                String employeeName = specifyNumberTableAddTextfield.getText();
                String employeePosition = specifyNumberTableAddPositionTextfield.getText();
                int employeeHoursWorked = Integer.parseInt(specifyNumberTableAddHoursTextfield.getText());

                Employee newEmployee = new Employee(employeeName);

                // We add the employee to the employees list
                // We update the JSON file
                // We display a success message
                // We display an error message
                // We update the restaurant object
                try {
                    String dataJson = new String(Files.readAllBytes(Paths.get("./json/employee.json")));
                    JSONArray myArray = new JSONArray(dataJson);
                    JSONObject newTableJson = new JSONObject();
                    newTableJson.put("name", employeeName);
                    newTableJson.put("position", employeePosition);
                    newTableJson.put("hoursWorked", employeeHoursWorked);
                    myArray.put(newTableJson);
                    String jsonString = myArray.toString();
                    Files.write(Paths.get("./json/employee.json"), jsonString.getBytes());
                    System.out.println("Le nouvel employé a été ajsouté.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
