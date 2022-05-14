package com.example.simongame;

import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import java.net.URL;
import java.io.File;

public class MainMenu implements Initializable {
    static public String path = "src/main/resources/Save.json";
    static public String KEY_RESULTS = "results";
    static public String KEY_NAME = "name";
    static public String KEY_SCORE = "score";
    private String sPathResource;
    @FXML
    protected ImageView backgroundMenu;
    @FXML
    private ImageView resultsButton, newGameButtonImageView, exitButtonImageView;
    @FXML
    private ImageView logoGame;
    @FXML
    private Button newGameButton, resultsBtn;
    private boolean haveResults = false;
    private final int height = (int)Screen.getPrimary().getBounds().getHeight();
    private final int width = (int)Screen.getPrimary().getBounds().getWidth();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showMenu();
        checkResults();
    }
    void settingBackgroundMenu() {
        backgroundMenu.setFitWidth(width);
        backgroundMenu.setFitHeight(height);
        logoGame.setFitWidth(width);
        logoGame.setFitHeight(height);
    }
    private void checkResults() {
        File file = new File(MainMenu.path);
        haveResults = file.exists();
        resultsDefaultImage();
    }
    private void showMenu() {
        sPathResource = new File("src/main/resources/").getAbsolutePath();
        settingBackgroundMenu();
    }
    @FXML
    private void goToNextScene(ActionEvent event) throws IOException {
        String name = "";
        Object source = event.getSource();
        if (newGameButton.equals(source)) {
            name = "SimonGame.fxml";
        } else if (resultsBtn.equals(source)) {
            name = "Results.fxml";
        }
        String css = Paths.get("./src/main/java/com/example/simongame/TextConfigurations.css").toAbsolutePath().toUri().toString();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(css);
        stage.getIcons().add(new Image(MainApplication.iconPath));
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    private void buttonPressed(ActionEvent event) throws IOException {
        switch(((Button)event.getSource()).getId()) {
            case "newGameButton", "resultsBtn" -> goToNextScene(event);
            case "exitButton" -> Platform.exit();
        }
    }
    @FXML
    private void mouseEntered(MouseEvent event) {
        switch(((Button)event.getSource()).getId()) {
            case "newGameButton" ->
                    newGameButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/NewGamePointed.png"));
            case "resultsBtn" -> {
                if(haveResults)
                    resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonRelease.png"));
            }
            case "exitButton" ->
                    exitButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ExitPointed.png"));
        }
    }
    @FXML
    private void mouseExited(MouseEvent event) {
        switch(((Button)event.getSource()).getId()) {
            case "newGameButton" ->
                    newGameButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/NewGame.png"));
            case "resultsBtn" -> resultsDefaultImage();
            case "exitButton" ->
                    exitButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/Exit.png"));
        }
    }
    private void resultsDefaultImage() {
        if(haveResults)
            resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonDefault.png"));
    }
    @FXML
    private void mousePressed(MouseEvent event) {
        switch(((Button)event.getSource()).getId()) {
            case "newGameButton" -> {
                PlaySound.play(Sounds.PLAY_TAP);
                newGameButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/NewGamePressed.png"));
            }
            case "resultsBtn" -> {
                if(haveResults) {
                    resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonPressed.png"));
                    PlaySound.play(Sounds.PLAY_TAP);
                }
            }
            case "exitButton" -> {
                PlaySound.play(Sounds.PLAY_TAP);
                exitButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ExitPressed.png"));
            }
        }
    }
    @FXML
    private void mouseReleased(MouseEvent event) {
        switch(((Button)event.getSource()).getId()) {
            case "newGameButton" ->
                    newGameButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/NewGamePointed.png"));
            case "resultsBtn" -> {
                if(haveResults)
                    resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonRelease.png"));
            }
            case "exitButton" ->
                    exitButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ExitPointed.png"));
        }
    }
}