package com.example.controller.dashboard;

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
import java.util.*;

public class MenuDashboardController implements Initializable {
    @FXML
    private Button backToMenu;
    @FXML
    private Label lessWealthy;
    @FXML
    private Label moreWealthy;
    @FXML
    private Label totalPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backToMenu.setOnMousePressed(mouseEvent -> {
            try {
                switchToDashboardPage(mouseEvent);
            } catch (IOException e) {
                throw new NullPointerException();
            }
        });
        Dashboard dashboard = new Dashboard();
        List<Ingredient> ingredients = new ArrayList<>();
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
        JSONArray myArray = new JSONArray(dishJson);
        JSONArray ingredientArray = new JSONArray(ingredientJson);

        ingredientArray.forEach(ingredientObj -> {
            JSONObject ingredientJSON = (JSONObject) ingredientObj;
            String name = ingredientJSON.getString("name");
            String type = ingredientJSON.getString("type");
            Ingredient ingredient = new Ingredient(name, type);
            ingredients.add(ingredient);
        });

        myArray.toList().stream().map(element -> (Map<String, Object>) element).map(JSONObject::new).forEach(myJSONObject -> {
            List<Ingredient> ingredientList = new ArrayList<>();

            myJSONObject.getJSONArray("ingredients").toList().forEach(ingredient -> ingredients.forEach(ingredient1 -> {
                if (ingredient1.getName().equals(ingredient)) {
                    ingredientList.add(ingredient1);
                }
            }));
            dashboard.addDish(new Dish(myJSONObject.getString("name"), myJSONObject.getString("description"),
                    Double.parseDouble(myJSONObject.getString("price")),
                    myJSONObject.getString("image"),
                    ingredientList));
        });
        List<Dish> sortedDishes = dashboard.getDishes().stream()
                .sorted(Comparator.comparing(Dish::getPrice))
                .toList();

        if (!sortedDishes.isEmpty()) {
            Dish lessWealthyDish = sortedDishes.get(0);
            lessWealthy.setText(lessWealthyDish.getName() + " " + lessWealthyDish.getPrice() + "€");
        }
        if (!sortedDishes.isEmpty()) {
            Dish moreWealthyDish = sortedDishes.get(sortedDishes.size() - 1);
            moreWealthy.setText(moreWealthyDish.getName() + " " + moreWealthyDish.getPrice() + "€");
        }
        double total = 0;
        for (Dish dish : sortedDishes) {
            total += dish.getPrice();
        }
        totalPrice.setText(total + "€");
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
