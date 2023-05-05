package com.example.controller.command;

import com.example.model.Command;
import com.example.model.Dashboard;
import com.example.model.Dish;
import com.example.model.Ingredient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ListCommandsController implements Initializable {
    @FXML
    private TableView<Command> commandTable;
    @FXML
    private TableColumn<Command, String> clientColumn;
    @FXML
    private TableColumn<Command, String> tableColumn;
    @FXML
    private TableColumn<Command, String> statusColumn;
    @FXML
    private TableColumn<Command, String> totalPriceColumn;
    @FXML
    private Label clientLabel;
    @FXML
    private Label tableLabel;
    @FXML
    private Label dishLabel;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private Button cancelCommandBtn;
    @FXML
    private Button homeButton;
    @FXML
    private Button addCommand;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("table"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        Dashboard dashboard = new Dashboard();
        String commandJson = "[]" ;
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            commandJson = new String(Files.readAllBytes(Paths.get("./json/commands.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }
        String dishJson = "[]" ;
        try {
            dishJson = new String(Files.readAllBytes(Paths.get("./json/dish.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }
        String ingredientJson = "[]" ;
        try {
            ingredientJson = new String(Files.readAllBytes(Paths.get("./json/ingredients.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }
        JSONArray myArray = new JSONArray(commandJson);
        JSONArray dishArray = new JSONArray(dishJson);
        JSONArray ingredientArray = new JSONArray(ingredientJson);

        ingredientArray.forEach(ingredientObj -> {
            JSONObject ingredientJSON = (JSONObject) ingredientObj;
            String name = ingredientJSON.getString("name");
            String type = ingredientJSON.getString("type");
            Ingredient ingredient = new Ingredient(name, type);
            ingredients.add(ingredient);
        });
        myArray.toList().stream().map(Map.class::cast).map(JSONObject::new).forEach(myJsonObject -> {
            List<Dish> dishesList = new ArrayList<>();
            List<Ingredient> ingredientsList = new ArrayList<>();
            int commandId = myJsonObject.getInt("id");
            myJsonObject.getJSONArray("dishes").toList().forEach(
                    dish -> dishArray.toList().stream().map(Map.class::cast).map(JSONObject::new).forEach(myDishJSON -> {
                        myDishJSON.getJSONArray("ingredients").toList().forEach(ingredient -> ingredients.forEach(ingredient1 -> {
                            if (ingredient1.getName().equals(ingredient)) {
                                ingredientsList.add(ingredient1);
                            }
                        }));
                        if (myDishJSON.getString("name").equals(dish.toString())) {
                            Dish myDish = new Dish(
                                    myDishJSON.getString("name"),
                                    myDishJSON.getString("description"),
                                    myDishJSON.getDouble("price"),
                                    myDishJSON.getString("image"),
                                    ingredientsList
                            );
                            dishesList.add(myDish);

                        }
                    })
            );
            Command command = new Command(
                    dishesList,
                    myJsonObject.getString("status"),
                    myJsonObject.getInt("table"),
                    myJsonObject.getString("client")
            );
            String totalPrice = dishesList.stream().reduce(
                    0.0,
                    (subtotal, dish) -> subtotal + dish.getPrice(),
                    Double::sum
            ).toString();
            command.setTotalPrice(totalPrice);
            command.setId(commandId);

            dashboard.addCommand(command);
            ObservableList<Command> commandList = FXCollections.observableArrayList(dashboard.getCommands().stream().filter(
                    command1 -> command1.getStatus().equals("in progress")
            ).toList());

            commandTable.setItems(commandList);
        });
        String statusJSON = "[]";
        try {
            statusJSON = new String(Files.readAllBytes(Paths.get("./json/status.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }
        JSONArray statusArray = new JSONArray(statusJSON);
        statusArray.forEach(statusObj -> {
            JSONObject statusJSON1 = (JSONObject) statusObj;
            String status = statusJSON1.getString("name");
            statusComboBox.getItems().add(status);
        });
        ObjectProperty<Command> selectedCommand = new SimpleObjectProperty<>();
        commandTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                clientLabel.setText(newSelection.getClient());
                tableLabel.setText(String.valueOf(newSelection.getTable()));
                List<String> dishesNames = new ArrayList<>();
                newSelection.getDishes().forEach(dish -> dishesNames.add(dish.getName()));
                dishLabel.setText(String.join(", ", dishesNames));
                statusComboBox.setValue(newSelection.getStatus());
                selectedCommand.set(newSelection);
            }
        });
        statusComboBox.setOnAction(event -> {
            if (selectedCommand.get() != null && !statusComboBox.getValue().equals("")) {
                selectedCommand.get().setStatus(statusComboBox.getValue());
                dashboard.updateCommand(selectedCommand.get());
                ObservableList<Command> commandList = FXCollections.observableArrayList(dashboard.getCommands());
                commandTable.setItems(commandList);
                try {
                    JSONArray updatedArray = new JSONArray();
                            myArray.toList().stream()
                                    .map(Map.class::cast)
                                    .map(JSONObject::new)
                                    .forEach(myJSONObject -> {
                                        if (myJSONObject.getInt("id") == selectedCommand.get().getId()) {
                                            myJSONObject.put("status", selectedCommand.get().getStatus());
                                        }
                                        updatedArray.put(myJSONObject);
                                    });
                    Files.writeString(Paths.get("./json/commands.json"), updatedArray.toString(2));
                } catch (IOException e) {
                    throw new JSONException(e);
                }
                try {
                    goToListScreen(event);
                } catch (IOException e) {
                    throw new JSONException(e);
                }
            }
        });
        cancelCommandBtn.setOnMousePressed(mouseEvent ->{
            if (selectedCommand.get() != null) {
                selectedCommand.get().setStatus("cancelled");
                ObservableList<Command> commandList = FXCollections.observableArrayList(dashboard.getCommands());
                commandTable.setItems(commandList);
                try {
                    JSONArray updatedArray = new JSONArray();
                            myArray.toList().stream()
                                    .map(Map.class::cast)
                                    .map(JSONObject::new)
                                    .forEach(myJSONObject -> {
                                        if (myJSONObject.getInt("id") == selectedCommand.get().getId()) {
                                            myJSONObject.put("status", selectedCommand.get().getStatus());
                                        }
                                        updatedArray.put(myJSONObject);
                                    });
                    Files.writeString(Paths.get("./json/commands.json"), updatedArray.toString(2));
                } catch (IOException e) {
                    throw new JSONException(e);
                }
            }
        });
        addCommand.setOnMousePressed(
                mouseEvent -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/command/formCommands.fxml"));
                        Parent root = loader.load();
                        Scene commandScene = new Scene(root);

                        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        currentStage.setScene(commandScene);
                        currentStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
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
    public void goToListScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/command/listCommands.fxml"));
        Parent root = loader.load();
        Scene commandScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(commandScene);
        currentStage.show();
    }
}
