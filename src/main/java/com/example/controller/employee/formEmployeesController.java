package com.example.controller.employee;

import com.example.model.Dish;
import com.example.model.Employee;
import com.example.model.Ingredient;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

//import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class formEmployeesController implements Initializable {
    @FXML
    private TextField nameEmployeeTextField;
    @FXML
    private TextField positionEmployeeTextField;
    @FXML
    private Button employeeButton;
    @FXML
    private Label employeeLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            List<Employee> employees = new ArrayList<>();
            try {
                String dataJson = new String(Files.readAllBytes(Paths.get("./json/employee.json")));
                JSONArray myArray = new JSONArray(dataJson);
                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    String name = myJSONObject.getString("name");
                    String position = myJSONObject.getString("position");
                    employees.add(new Employee(name, position));
                    employeeButton.getId().add(name);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        employeeButton.setOnAction(event -> {
            Object selectedItem = employeeButton.getValue();
            if (selectedItem != null) {
                Employee selectedEmployee = employees.stream()
                        .filter(employee -> employee.getName().equals(selectedItem.toString()))
                        .findFirst()
                        .orElseThrow(); // handle error here
                String employeeText = selectedEmployee.getName() + ", " + selectedEmployee.getPosition();
                employeeLabel.setText(employeeText);
            }
        });
    }



