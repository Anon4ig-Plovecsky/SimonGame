package com.example.simongame;

import javafx.scene.input.KeyCombination;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    int width = (int)Screen.getPrimary().getBounds().getWidth();
    int height = (int)Screen.getPrimary().getBounds().getHeight();
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(Paths.get("./src/main/java/resources/Lobster-Regular.ttf").toAbsolutePath().toUri().toString(), 50);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, width, height);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setTitle("Simon Game");
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}