package com.example.controller.command;

import com.example.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class FormCommandsController implements Initializable {
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
        clientCommandComboBox.setOnMousePressed(mouseEvent -> {
            String dataJson = "[]";
            List<Client> clients = new ArrayList<>();
            try {
                dataJson = new String(Files.readAllBytes(Paths.get("./json/client.json")));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            clientCommandComboBox.getItems().clear();
            JSONArray myArray = new JSONArray(dataJson);
            myArray.forEach(item -> {
                JSONObject myJSONObject = (JSONObject) item;
                clientCommandComboBox.getItems().add(myJSONObject.getString("name"));
                Client client = new Client(myJSONObject.getString("name"));
                clients.add(client);
            });

            if (clientCommandComboBox != null) {
                clientCommandComboBox.setOnAction(event -> {
                    Object selectedItem = clientCommandComboBox.getValue();
                    if (selectedItem != null) {
                        Client client = command.getNameClient(selectedItem.toString(), clients);
                        command.setClient(client.getName());
                        String clientName = client.getName();
                        clientCommandLabel.setText(clientName);
                    }
                });
            }
        });

        tableCommandComboBox.setOnMousePressed(mouseEvent -> {
            String dataJson = "[]";
            List<Table> tables = new ArrayList<>();
            try {
                dataJson = new String(Files.readAllBytes(Paths.get("./json/table.json")));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            tableCommandComboBox.getItems().clear();
            JSONArray myArray = new JSONArray(dataJson);

            myArray.forEach(item -> {
                JSONObject myJSONObject = (JSONObject) item;
                tableCommandComboBox.getItems().add(myJSONObject.getInt("number"));
                Table table = new Table(myJSONObject.getInt("number"),5,5);
                tables.add(table);
            });


            tableCommandComboBox.setOnAction(event -> {
                Object selectedItem = tableCommandComboBox.getValue();
                if (selectedItem != null) {
                    Table table = command.getTableNumber(Integer.parseInt(selectedItem.toString()), tables);
                    if (table != null) {
                        command.setTable(table.getNumber());
                        tableCommandLabel.setText(String.valueOf(Integer.parseInt(selectedItem.toString())));
                    }  // In case of table is null

                }
            });
        });
        dishCommandComboBox.setOnMousePressed(mouseEvent -> {
            Menu menu = new Menu();
            String dataJson = "[]";
            List<Ingredient> ingredients = new ArrayList<>();
            try {
                dataJson = new String(Files.readAllBytes(Paths.get("./json/dish.json")));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            String ingredientJson = "[]";
            try {
                ingredientJson = new String(Files.readAllBytes(Paths.get("./json/ingredients.json")));
            } catch (IOException e) {
                throw new JSONException(e);
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


            dishCommandComboBox.setOnAction(event -> {
                List<Dish> dishList = menu.getDishes();
                Object selectedItem = dishCommandComboBox.getValue();
                if (selectedItem != null) {
                    Dish dish = command.getDishesByName(selectedItem.toString(), dishList);
                    command.addDish(dish);
                    List<String> dishesText = command.getDishes().stream()
                            .map(Dish::getName)
                            .toList();
                    dishCommandLabel.setText(String.join(", ", dishesText));
                }
            });
        });
        commandButton.setOnMousePressed(mouseEvent -> {
            String dataJson = "[]";
            try {
                dataJson = new String(Files.readAllBytes(Paths.get("./json/commands.json")));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            JSONArray myArray = new JSONArray(dataJson);
            JSONObject commandJson = new JSONObject();
            commandJson.put("id", myArray.length() + 1);
            commandJson.put("client", command.getClient());
            commandJson.put("table", command.getTable());
            List<String> dishes = command.getDishes().stream()
                    .map(Dish::getName)
                    .toList();
            commandJson.put("dishes", dishes);
            commandJson.put("status", "in progress");
            myArray.put(commandJson);
            try {
                Files.writeString(Paths.get("./json/commands.json"), myArray.toString(2));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            try{
                goToMenu(mouseEvent);
            }catch (IOException e){
                throw new NullPointerException();
            }
        });
    }

    public void goToMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/command/listCommands.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
}
