package com.example.simongame;

import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.stage.Screen;
import javafx.fxml.FXML;
import java.net.URL;

public class MainMenu implements Initializable {
    @FXML
    protected ImageView backgroundMenu;
    int width = (int) Screen.getPrimary().getBounds().getWidth();
    int height = (int)Screen.getPrimary().getBounds().getHeight();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showMenu();
    }
    void settingBackgroundMenu() {
        backgroundMenu.setFitWidth(width);
        backgroundMenu.setFitHeight(height);
        //TODO backgroundMenu.setScaleX();
    }
    void showMenu() {
        settingBackgroundMenu();
    }
}
