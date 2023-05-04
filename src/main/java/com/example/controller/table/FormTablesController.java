package com.example.controller.table;

import com.example.model.Ingredient;
import com.example.model.Restaurant;
import com.example.model.Table;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FormTablesController implements Initializable {

    @FXML
    private Button displayTableList;
    @FXML
    private Label displayTablesLabel;
    @FXML
    private Button navToListTable;
    @FXML
    private TextField specifyTableTexfield;
    @FXML
    private Button deleteTableButton;

    @FXML
    void switchToTableListScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/table/listTables.fxml"));
        Parent root = loader.load();
        Scene tablesScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(tablesScene);
        currentStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Table table = new Table();
        Restaurant restaurant = new Restaurant();

        displayTableList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String dataJson = "[]";
                List tables = new ArrayList<>();
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/table.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JSONArray myArray = new JSONArray(dataJson);

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    if (myJSONObject.getBoolean("isFree")) {
                        displayTablesLabel.setText(displayTablesLabel.getText() + " id:" + myJSONObject.getInt("id"));
                    }
                }
            }
        });

        /*specifyTableTexfield.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (specifyTableTexfield != null) {
                    Object selectedItem = specifyTableTexfield.getText();
                    if (selectedItem != null) {
                        System.out.println(selectedItem);
                    }
                }
            }
        });*/

        deleteTableButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                List tables = new ArrayList<>();
                String dataJson = "[]";
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/table.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JSONArray myArray = new JSONArray(dataJson);

                if (specifyTableTexfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un numéro de table.");
                    return;
                }
                String tableNumberString = specifyTableTexfield.getText();
                int tableNumber = Integer.parseInt(tableNumberString);
                boolean isTableFound = false;

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    if (myJSONObject.getNumber("number").intValue() == tableNumber) {
                        isTableFound = true;
                        break;
                    }
                }

                deleteTableButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        List tables = new ArrayList<>();
                        List<Table> tablesRestaurant = restaurant.getTables();
                        String dataJson = "[]";
                        try {
                            dataJson = new String(Files.readAllBytes(Paths.get("./json/table.json")));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        JSONArray myArray = new JSONArray(dataJson);

                        if (specifyTableTexfield.getText().isEmpty()) {
                            System.out.println("Veuillez spécifier un numéro de table.");
                            return;
                        }
                        String tableNumberString = specifyTableTexfield.getText();
                        int tableNumber = Integer.parseInt(tableNumberString);
                        boolean isTableFound = false;

                        for (int i = 0; i < myArray.length(); i++) {
                            JSONObject myJSONObject = myArray.getJSONObject(i);
                            System.out.println(myJSONObject);
                            if (myJSONObject.getNumber("number").intValue() == tableNumber) {
                                isTableFound = true;
                                myArray.remove(i);
                                break;
                            }
                        }

                        if (isTableFound) {
                            // Mise à jour du fichier JSON
                            try {
                                String jsonString = myArray.toString();
                                Files.write(Paths.get("./json/table.json"), jsonString.getBytes());
                                System.out.println("La table numéro " + tableNumber + " a été supprimée.");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            // Mise à jour de l'objet Restaurant
                            restaurant.removeTable(tableNumber);
                        } else {
                            System.out.println("La table numéro " + tableNumber + " n'a pas été trouvée.");
                        }
                    }
                });
            }
        });

    }
}
