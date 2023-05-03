package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.*;

public class HomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}