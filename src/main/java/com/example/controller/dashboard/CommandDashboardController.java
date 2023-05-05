package com.example.controller.dashboard;

import com.example.model.Command;
import com.example.model.Dashboard;
import com.example.model.Dish;
import com.example.model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class CommandDashboardController implements Initializable {
    @FXML
    private Button backToMenu;
    @FXML
    private TableView<Command> commandTable;
    @FXML
    private TableView<Command> commandAlphaTable;
    @FXML
    private TableColumn<Command, String> clientColumn;
    @FXML
    private TableColumn<Command, String> tableColumn;
    @FXML
    private TableColumn<Command, String> statusColumn;
    @FXML
    private TableColumn<Command, String> priceColumn;
    @FXML
    private TableColumn<Command, String> clientAlphaColumn;
    @FXML
    private TableColumn<Command, String> tableAlphaColumn;
    @FXML
    private TableColumn<Command, String> statusAlphaColumn;
    @FXML
    private TableColumn<Command, String> priceAlphaColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("table"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        clientAlphaColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        tableAlphaColumn.setCellValueFactory(new PropertyValueFactory<>("table"));
        statusAlphaColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceAlphaColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));


        Dashboard dashboard = new Dashboard();
        String commandJson = "[]";
        List<Ingredient> ingredientList = new ArrayList<>();
        try {
            commandJson = new String(Files.readAllBytes(Paths.get("./json/commands.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }
        String dishJson = "[]";
        try {
            dishJson = new String(Files.readAllBytes(Paths.get("./json/dish.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }
        String ingredientJson = "[]";
        try {
            ingredientJson = new String(Files.readAllBytes(Paths.get("./json/ingredients.json")));
        } catch (IOException e) {
            throw new JSONException(e);
        }

        JSONArray commandArray = new JSONArray(commandJson);
        JSONArray dishArray = new JSONArray(dishJson);
        JSONArray ingredientArray = new JSONArray(ingredientJson);

        ingredientArray.forEach(ingredientObj -> {
            JSONObject ingredientJSON = (JSONObject) ingredientObj;
            String name = ingredientJSON.getString("name");
            String type = ingredientJSON.getString("type");
            Ingredient ingredient = new Ingredient(name, type);
            ingredientList.add(ingredient);
        });
        commandArray.toList().stream().map(Map.class::cast).map(JSONObject::new).forEach(myJsonObject -> {
            List<Dish> dishesList = new ArrayList<>();
            List<Ingredient> ingredientsList = new ArrayList<>();
            myJsonObject.getJSONArray("dishes").toList().forEach(
                    dish -> dishArray.toList().stream().map(Map.class::cast).map(JSONObject::new).forEach(myDishJSON -> {
                        myDishJSON.getJSONArray("ingredients").toList().forEach(ingredient -> ingredientsList.forEach(ingredient1 -> {
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
            DecimalFormat df = new DecimalFormat("#.##");
            String totalPrice = df.format(dishesList.stream().reduce(
                    0.0,
                    (subtotal, dish) -> subtotal + dish.getPrice(),
                    Double::sum
            ));
            command.setTotalPrice(totalPrice);
            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            command.setDate(date);

            dashboard.addCommand(command);
            ObservableList<Command> commandList = FXCollections.observableArrayList(dashboard.getCommands().stream().filter(
                    command1 -> command1.getStatus().equals("served")
            ).toList());


            commandTable.setItems(commandList);

            ObservableList<Command> commandAlphaList = FXCollections.observableArrayList(dashboard.getCommands().stream()
                    .sorted(Comparator.comparing(Command::getClient).thenComparing(Command::getDate)).toList());
            commandAlphaTable.setItems(commandAlphaList);
        });
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
