package com.example.controller;

import com.example.model.Dish;
import com.example.model.Ingredient;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private ComboBox ingreComboBox;
    @FXML
    private Label ingreLabel;
    @FXML
    private Button dishButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Dish dish = new Dish();
        ingreComboBox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String dataJson = "[]";
                try {
                    dataJson = new String(Files.readAllBytes(Paths.get("./json/ingredients.json")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JSONArray myArray = new JSONArray(dataJson);
                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject myJSONObject = myArray.getJSONObject(i);
                    ingreComboBox.getItems().add(myJSONObject.getString(("name")));
                }
            }

        });
        if (ingreComboBox != null) {
            ingreComboBox.setOnAction(event -> {
                Object selectedItem = ingreComboBox.getValue();
                if (selectedItem != null) {
                    Ingredient ingredient = dish.getIngredientByName(selectedItem.toString());
                    //dish.addIngredient(ingredient);
                    System.out.println(ingredient);
                    //System.out.println(dish.getIngredients());
                }
            });
        }
        //List<Ingredient> ingredientList = dish.getIngredients()
          //      .stream()
            //    .forEach(ingredient -> {System.out.println(ingredient);})
    }
}