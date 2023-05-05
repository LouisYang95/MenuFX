package com.example.controller.employee;

import com.example.model.Employee;
import com.example.model.Restaurant;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Restaurant restaurant = new Restaurant();

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

                displayEmployeeLabel.setText("");

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    displayEmployeeLabel.setText(displayEmployeeLabel.getText() + " name:" + myJSONObject.getString("name"));
                }
            }
        });

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

                if (specifyNumberTableDeleteTextfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un nom d'employé.");
                    return;
                }
                String tableNumberString = specifyNumberTableDeleteTextfield.getText();
                String employeeName = String.valueOf(tableNumberString);
                boolean isEmployeeFound = false;

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

    }
}
