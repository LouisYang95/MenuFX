package com.example.controller.home;

import com.example.model.Chrono;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Label timeLabel;
    /**
     * This method is used to switch to the dish page
     * @param event
     * @throws IOException
     */

    public void switchToDishPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dish/listDishes.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    /**
     * This method is used to switch to the command page
     * @param event
     * @throws IOException
     */
    public void switchToCommandPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/command/listCommands.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    /**
     * This method is used to switch to the table page
     * @param event
     * @throws IOException
     */
    public void switchToTablePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/table/formTables.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    /**
     * This method is used to switch to the employee page
     * @param event
     * @throws IOException
     */
    public void switchToEmployeePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/employee/formEmployees.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
    /**
     * This method is used to switch to the dashboard page
     * @param event
     * @throws IOException
     */
    public void switchToDashboardPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dashboard/dashBoard.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            //Start the chronometer
            if (!Chrono.isRunning) {
                Chrono chrono = new Chrono(timeLabel);
                chrono.start();
                timeLabel.setText(Chrono.getRemainingTime());
            }

            if (Chrono.isRunning) {
                Chrono.setTimerLabel(timeLabel);
            }
        }
}