package com.example.controller.dashboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @FXML
    private Button commandButton;
    @FXML
    private Button employeeButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button faxButton;
    @FXML
    private Button backToMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        commandButton.setOnMousePressed(mouseEvent -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dashboard/commandDashboard.fxml"));
                Parent root = loader.load();
                Scene commandScene = new Scene(root);

                Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                currentStage.setScene(commandScene);
                currentStage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        employeeButton.setOnMousePressed(mouseEvent->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dashboard/employeeDashboard.fxml"));
                Parent root = loader.load();
                Scene employeeScene = new Scene(root);

                Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                currentStage.setScene(employeeScene);
                currentStage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        menuButton.setOnMousePressed(mouseEvent->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dashboard/menuDashboard.fxml"));
                Parent root = loader.load();
                Scene menuScene = new Scene(root);

                Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                currentStage.setScene(menuScene);
                currentStage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        faxButton.setOnMousePressed(
                mouseEvent -> {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dashboard/faxDashboard.fxml"));
                        Parent root = loader.load();
                        Scene faxScene = new Scene(root);

                        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        currentStage.setScene(faxScene);
                        currentStage.show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        );
        backToMenu.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/home/home.fxml"));
                Parent root = loader.load();
                Scene dishScene = new Scene(root);

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(dishScene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
