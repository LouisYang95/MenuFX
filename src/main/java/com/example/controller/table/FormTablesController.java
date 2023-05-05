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
import javafx.scene.text.Text;
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
    private Button displayTableFreeList;
    @FXML
    private Button displayTableNonFreeList;
    @FXML
    private Label displayTablesLabel;
    @FXML
    private Label displayTablesFreeLabel;
    @FXML
    private Label displayTablesNonFreeLabel;
    @FXML
    private TextField specifyTableTexfield;
    @FXML
    private Button deleteTableButton;
    @FXML
    private Button registerTableButton;
    @FXML
    private TextField specifyTableIdTextfield;
    @FXML
    private TextField specifyTableNumberTextfield;
    @FXML
    private TextField specifyNumberTableDeleteButton;

    @FXML
    private Button homeButton;
    @FXML
    void switchToTableListScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/table/formTables.fxml"));
        Parent root = loader.load();
        Scene tablesScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(tablesScene);
        currentStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

                // Efface le texte précédent
                displayTablesLabel.setText("");

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    displayTablesLabel.setText(displayTablesLabel.getText() + " id:" + myJSONObject.getInt("id"));
                }
            }
        });

        displayTableFreeList.setOnMousePressed(new EventHandler<MouseEvent>() {
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

                // Efface le texte précédent
                displayTablesFreeLabel.setText("");

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    if (myJSONObject.getBoolean("isFree")) {
                        displayTablesFreeLabel.setText(displayTablesFreeLabel.getText() + " id:" + myJSONObject.getInt("id"));
                    }
                }
            }
        });

        displayTableNonFreeList.setOnMousePressed(new EventHandler<MouseEvent>() {
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

                // Efface le texte précédent
                displayTablesNonFreeLabel.setText("");

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    if (!myJSONObject.getBoolean("isFree")) {
                        displayTablesNonFreeLabel.setText(displayTablesNonFreeLabel.getText() + " id:" + myJSONObject.getInt("id"));
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
                List<Table> tablesRestaurant = restaurant.getTables();
                String dataJson = "[]";
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/table.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JSONArray myArray = new JSONArray(dataJson);

                if (specifyNumberTableDeleteButton.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un numéro de table.");
                    return;
                }
                String tableNumberString = specifyNumberTableDeleteButton.getText();
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

        registerTableButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                List<Table> tablesRestaurant = restaurant.getTables();
                int maxId = tablesRestaurant.stream().mapToInt(Table::getId).max().orElse(0);

                if (specifyTableTexfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un nombre de places pour la nouvelle table.");
                    return;
                }

                if (specifyTableIdTextfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un identifiant pour la nouvelle table.");
                    return;
                }

                if (specifyTableNumberTextfield.getText().isEmpty()) {
                    System.out.println("Veuillez spécifier un numéro pour la nouvelle table.");
                    return;
                }

                int id = Integer.parseInt(specifyTableIdTextfield.getText());
                int numero = Integer.parseInt(specifyTableNumberTextfield.getText());
                int nbSeats = Integer.parseInt(specifyTableTexfield.getText());

                // Création de la nouvelle table avec un ID et un numéro spécifié
                Table newTable = new Table( numero, nbSeats);

                // Mise à jour du fichier JSON
                try {
                    String dataJson = new String(Files.readAllBytes(Paths.get("./json/table.json")));
                    JSONArray myArray = new JSONArray(dataJson);
                    JSONObject newTableJson = new JSONObject();
                    newTableJson.put("id", id);
                    newTableJson.put("number", numero);
                    newTableJson.put("nbSeats", nbSeats);
                    newTableJson.put("isFree", newTable.isFree());
                    newTableJson.put("command", newTable.getCommand());
                    myArray.put(newTableJson);
                    String jsonString = myArray.toString();
                    Files.write(Paths.get("./json/table.json"), jsonString.getBytes());
                    System.out.println("La nouvelle table a été ajoutée.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        homeButton.setOnAction(event -> {
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
