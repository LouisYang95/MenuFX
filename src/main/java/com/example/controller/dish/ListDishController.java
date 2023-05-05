package com.example.controller.dish;

import com.example.model.Dish;
import com.example.model.Ingredient;
import com.example.model.Menu;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Map;
import java.util.ResourceBundle;

public class ListDishController implements Initializable {
    @FXML
    private TableView<Dish> dishTable;
    @FXML
    private TableColumn<Dish, String> tabColName;
    @FXML
    private TableColumn<Dish, String> tabColPrice;
    @FXML
    private ImageView imgLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label ingredientLabel;
    @FXML
    private Button addDishButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tabColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        Menu menu = new Menu();
        String dishJson = "[]";
        List<Ingredient> ingredients = new ArrayList<>();
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
            menu.addDish(new Dish(myJSONObject.getString("name"), myJSONObject.getString("description"),
                    Double.parseDouble(myJSONObject.getString("price")),
                    myJSONObject.getString("image"),
                    ingredientList));
            //create a ObservableList from the menu's dishes
            ObservableList<Dish> dishes = FXCollections.observableArrayList(menu.getDishes());
            dishTable.setItems(dishes);
        });
        dishTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameLabel.setText(newSelection.getName());
                descLabel.setText(newSelection.getDescription());
                priceLabel.setText(String.valueOf(newSelection.getPrice()));

                List<String> ingredientNames = new ArrayList<>();
                newSelection.getIngredients().forEach(ingredient -> ingredientNames.add(ingredient.getName()));
                ingredientLabel.setText(String.join(", ", ingredientNames));

                String imageUrl = newSelection.getImage();
                if (imageUrl != null) {
                    System.out.println(imageUrl);
                    Image image = new Image(imageUrl);
                    imgLabel.setImage(image);
                } else {
                    imgLabel.setImage(null);
                }
            }
        });
        addDishButton.setOnMousePressed(event -> {
            try {
                switchToThisScreen(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void switchToThisScreen(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vue/dish/formDish.fxml"));
        Parent root = loader.load();
        Scene dishScene = new Scene(root);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(dishScene);
        currentStage.show();
    }
}
