package com.example.controller.command;

import com.example.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class formCommandsController implements Initializable {
    @FXML
    private ComboBox clientCommandComboBox;
    @FXML
    private ComboBox tableCommandComboBox;
    @FXML
    private ComboBox dishCommandComboBox;
    @FXML
    private Label clientCommandLabel;
    @FXML
    private Label tableCommandLabel;
    @FXML
    private Label dishCommandLabel;
    @FXML
    private Button commandButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Command command = new Command();

        clientCommandComboBox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String dataJson = "[]";
                List clients = new ArrayList<>();
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/client.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                clientCommandComboBox.getItems().clear();
                JSONArray myArray = new JSONArray(dataJson);

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    clientCommandComboBox.getItems().add(myJSONObject.getString("name"));
                    Client client = new Client(myJSONObject.getString("name"));
                    clients.add(client);
                }

                if (clientCommandComboBox != null) {
                    clientCommandComboBox.setOnAction(event -> {
                        Object selectedItem = clientCommandComboBox.getValue();
                        if (selectedItem != null) {
                            Client client = command.getNameClient(selectedItem.toString(), clients);
                            command.setIdClient(client.getId());
                            String clientName = client.getName();
                            clientCommandLabel.setText(clientName);
                        }
                    });
                }
            }
        });

        tableCommandComboBox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String dataJson = "[]";
                List<Table> tables = new ArrayList<>();
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/table.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tableCommandComboBox.getItems().clear();
                JSONArray myArray = new JSONArray(dataJson);

                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    System.out.println(myJSONObject);
                    int number = myJSONObject.getInt("number");
                    tableCommandComboBox.getItems().add(String.valueOf(number));
                    Table table = new Table(number);
                    tables.add(table);
                }

                if (tableCommandComboBox != null) {
                    tableCommandComboBox.setOnAction(event -> {
                        Object selectedItem = tableCommandComboBox.getValue();
                        if (selectedItem != null) {
                            Table table = command.getTableNumber(Integer.parseInt(selectedItem.toString()), tables);
                            System.out.println(Integer.parseInt(selectedItem.toString()));
                            if (table != null) {
                                command.setTable(table.getNumber());
                                System.out.println(Integer.parseInt(selectedItem.toString()));
                                tableCommandLabel.setText(String.valueOf(Integer.parseInt(selectedItem.toString())));
                            } else {
                                // In case of table is null
                            }
                        }
                    });
                }

            }
        });



        dishCommandComboBox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Menu menu = new Menu();
                String dataJson = "[]";
                List<Ingredient> ingredients = new ArrayList<>();
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/dish.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String ingredientJson = "[]";
                try{
                    ingredientJson = new String(Files.readAllBytes(Paths.get("./json/ingredients.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dishCommandComboBox.getItems().clear();

                JSONArray myArray = new JSONArray(dataJson);
                JSONArray myArrayIngredients = new JSONArray(ingredientJson);
                myArrayIngredients.forEach(ingredientObj -> {
                    JSONObject ingredientJSON = (JSONObject) ingredientObj;
                    String name = ingredientJSON.getString("name");
                    String type = ingredientJSON.getString("type");
                    Ingredient ingredient = new Ingredient(name, type);
                    ingredients.add(ingredient);
                });
                myArray.toList().stream().map(element -> (Map<String, Object>) element).map(JSONObject::new).forEach(myJSONObject -> {
                    List<Ingredient> ingredientList = new ArrayList<>();

                    myJSONObject.getJSONArray("ingredients").toList().forEach(ingredient -> ingredients.stream().forEach(ingredient1 -> {
                        if (ingredient1.getName().equals(ingredient)) {
                            ingredientList.add(ingredient1);
                        }
                    }));
                    menu.addDish(new Dish(myJSONObject.getString("name"), myJSONObject.getString("description"),
                            Double.parseDouble(myJSONObject.getString("price")),
                            myJSONObject.getString("image"),
                            ingredientList));
                    dishCommandComboBox.getItems().add(myJSONObject.getString("name"));
                });


                if (dishCommandComboBox != null) {
                    dishCommandComboBox.setOnAction(event -> {
                        List<Dish> dishes = menu.getDishes();
                        Object selectedItem = dishCommandComboBox.getValue();
                        if (selectedItem != null) {
                            Dish dish = (Dish) command.getDishes(selectedItem.toString(), dishes);
                            command.addDish(dish);
                            List<String> dishesText = dish.getIngredients().stream()
                                    .map(Dish-> dish.getName())
                                    .toList();
                            dishCommandLabel.setText(String.join(", ", dishesText));
                        }
                    });
                }
            }
        });

    }
}
