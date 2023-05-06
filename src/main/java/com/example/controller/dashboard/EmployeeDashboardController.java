package com.example.controller.dashboard;

import com.example.model.Dashboard;
import com.example.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ResourceBundle;

public class EmployeeDashboardController implements Initializable {
    @FXML
    private Button backToMenu;
    @FXML
    private Label statMoins30;
    @FXML
    private Label statPlus30;
    @FXML
    private Label statMoins45;
    @FXML
    private Label statPlus45;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Dashboard dashboard = new Dashboard();
        String employeeJson = "[]";
        try {
            employeeJson = new String(Files.readAllBytes(Paths.get("./json/employee.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }

        JSONArray employeeArray = new JSONArray(employeeJson);

        employeeArray.toList().stream().map(Map.class::cast).map(JSONObject::new).forEach(myJsonObject -> {
            Employee employee = new Employee(myJsonObject.getString("name"),
                    myJsonObject.getInt("age"));
            dashboard.addEmployee(employee);
        });
        long totalEmployees = dashboard.getEmployees().size();
        long youngEmployees = dashboard.getEmployees().stream()
                .filter(employee -> employee.getAge() < 30)
                .count();
        double percentageYoung = ((double) youngEmployees / totalEmployees) * 100.0;
        statMoins30.setText(String.format("%.2f", percentageYoung) + "%");
        statPlus30.setText(String.format("%.2f", 100.0 - percentageYoung) + "%");

        long oldEmployees = dashboard.getEmployees().stream()
                .filter(employee -> employee.getAge() >= 45)
                .count();
        double percentageOld = ((double) oldEmployees / totalEmployees) * 100.0;
        statPlus45.setText(String.format("%.2f", percentageOld) + "%");
        statMoins45.setText(String.format("%.2f", 100.0 - percentageOld) + "%");


        backToMenu.setOnMousePressed(mouseEvent -> {
            try {
                switchToDashboardPage(mouseEvent);
            } catch (IOException e) {
                throw new NullPointerException();
            }
        });
    }
    public void switchToDashboardPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dashboard/dashBoard.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
}