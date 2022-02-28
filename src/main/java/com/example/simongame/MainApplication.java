package com.example.simongame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    int width = (int)Screen.getPrimary().getBounds().getWidth();
    int height = (int)Screen.getPrimary().getBounds().getHeight();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setTitle("Simon Game");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}