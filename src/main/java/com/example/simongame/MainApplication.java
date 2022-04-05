package com.example.simongame;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.builder.SpringApplicationBuilder;
import javafx.scene.input.KeyCombination;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public static final String iconPath = Paths.get("/src/main/resources/SimonGame.png").toAbsolutePath().toUri().toString();
    int width = (int)Screen.getPrimary().getBounds().getWidth();
    int height = (int)Screen.getPrimary().getBounds().getHeight();
    @Override
    public void start(Stage stage) throws IOException {
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
        stage.getIcons().add(new Image(MainApplication.iconPath));
        stage.show();
    }
    private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MainApplication.class);
        String[] args = getParameters().getRaw().toArray(String[]::new);
        builder.headless(false);
        return builder.run(args);
    }
    public static void main(String[] args) {
        launch();
    }
}