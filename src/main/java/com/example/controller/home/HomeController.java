package com.example.controller.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public void switchToDishPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dish/listDishes.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    public void switchToCommandPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/command/listCommands.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    public void switchToTablePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/table/formTables.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    public void switchToEmployeePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/employee/formEmployees.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    public void switchToDashboardPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dashboard/dashBoard.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
}
