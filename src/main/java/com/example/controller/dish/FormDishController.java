package com.example.controller.dish;

import com.example.model.Dish;
import com.example.model.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
import java.util.ResourceBundle;

public class FormDishController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private ComboBox<String> ingreComboBox;
    @FXML
    private Label ingreLabel;
    @FXML
    private Button dishButton;
    @FXML
    private Label dishesListLabel;
    @FXML
    private Button backToList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Dish dish = new Dish();
        /**
         * Add ingredients to the dish
         * @param mouseEvent
         */
        ingreComboBox.setOnMousePressed(mouseEvent -> {
            String dataJson = "[]";
            List<Ingredient> ingredients = new ArrayList<>();
            try {
                dataJson = new String(Files.readAllBytes(Paths.get("./json/ingredients.json")));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            JSONArray myArray = new JSONArray(dataJson);
            myArray.forEach(item -> {
                JSONObject myJSONObject = (JSONObject) item;
                ingreComboBox.getItems().add(myJSONObject.getString(("name")));
                Ingredient ingredient = new Ingredient(myJSONObject.getString("name"), myJSONObject.getString("type"));
                ingredients.add(ingredient);
            });

            if (ingreComboBox != null) {
                ingreComboBox.setOnAction(event -> {
                    String selectedItem = ingreComboBox.getValue();
                    if (selectedItem != null) {
                        Ingredient ingredient = dish.getIngredientByName(selectedItem, ingredients);
                        dish.addIngredient(ingredient);
                        List<String> ingredientText = dish.getIngredients().stream()
                                .map(Ingredient::getName)
                                .toList();
                        ingreLabel.setText(String.join(", ", ingredientText));
                    }
                });
            }
        });

        /**
         * Add dish(es)
         * @param mouseEvent
         */
        dishButton.setOnMousePressed(mouseEvent -> {
            String dataJson = "[]";
            try {
                dataJson = new String(Files.readAllBytes(Paths.get("./json/dish.json")));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            JSONArray myArray = new JSONArray(dataJson);
            JSONObject myJSONObject = new JSONObject();
            myJSONObject.put("name", nameTextField.getText());
            myJSONObject.put("description", descTextField.getText());
            myJSONObject.put("price", priceTextField.getText());
            myJSONObject.put("image", imageTextField.getText());
            List<String> ingredientNames = dish.getIngredients().stream()
                    .map(Ingredient::getName)
                    .toList();
            myJSONObject.put("ingredients", ingredientNames);
            myArray.put(myJSONObject);
            try {
                Files.writeString(Paths.get("./json/dish.json"), myArray.toString(2));
            } catch (IOException e) {
                throw new JSONException(e);
            }
            dishesListLabel.setText("Dish added");

            try {
                goToListScreen(mouseEvent);
            } catch (IOException e) {
                throw new NullPointerException();
            }
        });

        /**
         * Go back to the list of dishes
         * @param mouseEvent
         */
        backToList.setOnMousePressed(
                mouseEvent -> {
                    try {
                        goToListScreen(mouseEvent);
                    } catch (IOException e) {
                        throw new NullPointerException();
                    }
                }
        );

    }

    /**
     * Go to the list of dishes
     * @param event
     * @throws IOException
     */
    public void goToListScreen(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dish/listDishes.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
}