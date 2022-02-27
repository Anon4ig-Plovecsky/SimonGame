package com.example.simongame;

import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}