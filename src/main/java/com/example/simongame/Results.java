package com.example.simongame;

import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import org.json.JSONTokener;
import javafx.stage.Screen;
import javafx.scene.Parent;
import org.json.JSONObject;
import org.json.JSONArray;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import java.io.*;

public class Results {
    private String path;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView mainMenuButton;
    @FXML
    private ImageView resultsText;
    @FXML
    private Label textViewName;
    @FXML
    private Label textViewScore;
    private final double width = Screen.getPrimary().getBounds().getWidth();
    private final double height = Screen.getPrimary().getBounds().getHeight();
    private final double ratioWidth = width / 1800.0;
    private final double ratioHeight = height / 1125.0;
    @FXML
    void initialize() {
        setUpScene();
    }
    private void setUpScene() {
        path = new File("src/main/resources/").getAbsolutePath();
        setBackgroundImage();
        setMainMenuButton();
        setResultsText();
        setTextView();
    }
    private void fillTextView(int index, String name, int score) {
        String currentTextName = textViewName.getText();
        currentTextName += "\n" + " ".repeat(Math.max(0, 2 - Integer.toString(index).length())) + index + ". " + name;
        textViewName.setText(currentTextName);
        String currentTextScore = textViewScore.getText();
        currentTextScore += "\n" + score;
        textViewScore.setText(currentTextScore);
    }
    private void setTextView() {
        Font font = Font.loadFont("file:src/main/resources/Lobster-Regular.ttf", getDpY(65));
        textViewName.setPrefWidth(getDpX(1333));
        textViewName.setPrefHeight(getDpY(890));
        StackPane.setMargin(textViewName, new Insets(getDpY(170), 0, 0, getDpX(340)));
        textViewName.setFont(font);
        textViewScore.setPrefHeight(getDpY(890));
        textViewScore.setPrefWidth(getDpX(383));
        StackPane.setMargin(textViewScore, new Insets(getDpY(170), 0, 0, getDpX(800)));
        textViewScore.setFont(font);
        JSONObject results = new JSONObject();
        try {
            InputStream inputStream = new FileInputStream(MainMenu.path);
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            results = new JSONObject(jsonTokener);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONArray array = results.getJSONArray(MainMenu.KEY_RESULTS);
        for(int i = 0; i < array.length(); i++)
            fillTextView(i + 1, array.getJSONObject(i).getString(MainMenu.KEY_NAME),
                    array.getJSONObject(i).getInt(MainMenu.KEY_SCORE));
    }
    private void setBackgroundImage() {
        backgroundImage.setFitHeight(height);
        backgroundImage.setFitWidth(width);
    }
    private void setResultsText() {
        resultsText.setFitHeight(getDpY(128));
        resultsText.setFitWidth(getDpX(645));
        StackPane.setMargin(resultsText, new Insets(getDpY(60), 0, 0, getDpX(350)));
    }
    private void setMainMenuButton() {
        mainMenuButton.setFitHeight(getDpY(160));
        StackPane.setMargin(mainMenuButton, new Insets(getDpX(30), 0, 0, getDpY(30)));
    }
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        String name = "MainView.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.getIcons().add(new Image(MainApplication.iconPath));
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    private void mainMenuButtonOnEntered() {
        mainMenuButton.setImage(new Image("file:" + path + "/Image/Results/MainMenuButton/MainMenuButtonRelease.png"));
    }
    @FXML
    private void mainMenuButtonOnExited() {
        mainMenuButton.setImage(new Image("file:" + path + "/Image/Results/MainMenuButton/MainMenuButtonDefault.png"));
    }
    @FXML
    private void mainMenuButtonOnPressed() {
        mainMenuButton.setImage(new Image("file:" + path + "/Image/Results/MainMenuButton/MainMenuButtonPressed.png"));
        PlaySound.play(Sounds.PLAY_TAP);
    }
    @FXML
    private void mainMenuButtonOnReleased() {
        mainMenuButton.setImage(new Image("file:" + path + "/Image/Results/MainMenuButton/MainMenuButtonRelease.png"));
    }
    private double getDpX(double px) {
        return px * ratioWidth;
    }
    private double getDpY(double px) {
        return px * ratioHeight;
    }
}
