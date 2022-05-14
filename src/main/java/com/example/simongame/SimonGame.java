package com.example.simongame;

import javafx.scene.input.KeyCombination;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.Random;
import javafx.fxml.FXML;
import java.io.File;
import java.net.URL;
import java.util.*;

public class SimonGame implements Initializable {
    private final int maxLength = 17;
    private final Random random = new Random();
    private Demonstration demonstration;
    private Score score;
    private int totalScore = 0;
    private String rsc;
    @FXML
    private Button centerButton, greenButton, yellowButton, redButton, blueButton, mainMenuButton, saveResultButton;
    @FXML
    private ImageView saveResultButtonImage, mainMenuButtonImage, firstNumberScore, scoreImage, background, body;
    @FXML
    private ImageView centerButtonImage, yellowButtonImage, greenButtonImage, redButtonImage, blueButtonImage;
    @FXML
    private Label trueSubsequenceLabel;
    @FXML
    private TextField editTextYourName;
    @FXML
    private StackPane stackPane;
    private boolean record = false;
    public boolean playersTurn = false;
    public Queue<Integer> trueSubsequence = new LinkedList<>();
    public Queue<Integer> userSubsequence = new LinkedList<>();
    private final double width = (int)Screen.getPrimary().getBounds().getWidth();
    private final double height = (int)Screen.getPrimary().getBounds().getHeight();
    private final double trueWidth = width / 1800.0;
    private final double trueHeight = height / 1125.0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
        centerButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/CenterButton/CenterButtonLitUp.png"));
        score = new Score(stackPane, firstNumberScore, rsc);
    }
    private void setPath() {
        rsc = new File("src/main/resources/").getAbsolutePath();
    }
    private void setUpScene() {
        background.setFitHeight(height);
        background.setFitWidth(width);
        setPath();
        setBody();
        setScore();
        setMainMenuButton();
        setSaveResultButton();
        setCenterButton();
        setFirstNumberScore();
        setYellowButton();
        setGreenButton();
        setRedButton();
        setBlueButton();
        setEditTextYourName();
        editTextYourNameHasChanged();
    }
    @FXML
    public void gameRun() {
        if(demonstration != null && demonstration.isAlive())
            demonstration.interrupt();
        saveResultButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/SaveResultButton/SaveInactive.png"));
        record = false;
        score.deleteScore();
        totalScore = 0;
        score.setupScore(totalScore);
        trueSubsequence = new LinkedList<>();
        continueDemonstration();
        playersTurn = true;
    }
    public void continueDemonstration() {
        allButtonsDisable();
        StringBuilder stringSubsequence= new StringBuilder();
        trueSubsequence = addSubsequence();
        userSubsequence = new LinkedList<>();
        for (Integer integer : trueSubsequence) {
            userSubsequence.offer(integer);
            stringSubsequence.append(integer.toString()).append(" ");
        }
        trueSubsequenceLabel.setText(stringSubsequence.toString());
        demonstration = new Demonstration(rsc, greenButtonImage, redButtonImage, blueButtonImage, yellowButtonImage,
                trueSubsequence);
        demonstration.start();
    }
    private Queue<Integer> addSubsequence() {
        trueSubsequence.offer(random.nextInt(4));
        return trueSubsequence;
    }
    private void setBody() {
        body.setFitHeight(getDpY(1122));
        body.setFitWidth(getDpX(1122));
    }
    private void allButtonsDisable() {
        greenButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Green/GreenDefault.png"));
        redButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Red/RedDefault.png"));
        blueButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Blue/BlueDefault.png"));
        yellowButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Yellow/YellowDefault.png"));
    }
    private void setSaveResultButton() {
        saveResultButtonImage.setFitHeight(getDpY(150));
        saveResultButton.setPrefHeight(getDpY(150));
        saveResultButton.setPrefWidth(getDpX(559));
        StackPane.setMargin(saveResultButton, new Insets(0, getDpX(5), getDpY(30), 0));
        StackPane.setMargin(saveResultButtonImage, new Insets(0, getDpX(5), getDpY(30), 0));
    }
    private void setEditTextYourName() {
        editTextYourName.setMaxWidth(getDpX(465));
        editTextYourName.setMinWidth(getDpX(465));
        editTextYourName.setPrefWidth(getDpX(465));
        editTextYourName.setMinHeight(getDpY(105));
        editTextYourName.setMaxHeight(getDpY(105));
        editTextYourName.setPrefHeight(getDpY(105));
        Font font = Font.loadFont("file:src/main/resources/Lobster-Regular.ttf", getDpY(50));
        editTextYourName.setFont(font);
        StackPane.setMargin(editTextYourName, new Insets(0, getDpX(15), getDpY(200), 0));
    }
    private void setFirstNumberScore() {
        firstNumberScore.setFitHeight(getDpY(94));
        firstNumberScore.setFitWidth(getDpX(67));
        StackPane.setMargin(firstNumberScore, new Insets(0, getDpX(60), getDpY(160), 0));
    }
    private void setScore() {
        scoreImage.setFitHeight(getDpY(92));
        scoreImage.setFitWidth(getDpX(241));
        StackPane.setMargin(scoreImage, new Insets(0, getDpX(40), getDpY(400), 0));
    }
    private void setCenterButton() {
        centerButtonImage.setFitHeight(getDpY(354));
        centerButtonImage.setFitWidth(getDpX(354));
        centerButton.setPrefWidth(getDpX(257));
        centerButton.setPrefHeight(getDpY(232));
    }
    private void setBlueButton() {
        blueButtonImage.setFitHeight(getDpY(472));
        blueButtonImage.setFitWidth(getDpX(473));
        blueButtonImage.setLayoutX(getDpX(924));
        blueButtonImage.setLayoutY(getDpY(594));
        blueButton.setPrefWidth(getDpX(438));
        blueButton.setPrefHeight(getDpY(238));
        blueButton.setLayoutX(getDpX(935));
        blueButton.setLayoutY(getDpY(689));
    }
    private void setRedButton() {
        redButtonImage.setFitHeight(getDpY(473));
        redButtonImage.setFitWidth(getDpX(473));
        redButtonImage.setLayoutX(getDpX(930));
        redButtonImage.setLayoutY((getDpY(72)));
        redButton.setPrefWidth(getDpX(438));
        redButton.setPrefHeight(getDpY(238));
        redButton.setLayoutX(getDpX(935));
        redButton.setLayoutY(getDpY(194));
    }
    private void setYellowButton() {
        yellowButtonImage.setFitHeight(getDpY(472));
        yellowButtonImage.setFitWidth(getDpX(472));
        yellowButtonImage.setLayoutX(getDpX(402));
        yellowButtonImage.setLayoutY(getDpY(594));
        yellowButton.setPrefWidth(getDpX(438));
        yellowButton.setPrefHeight(getDpY(238));
        yellowButton.setLayoutX(getDpX(429));
        yellowButton.setLayoutY(getDpY(689));
    }
    private void setGreenButton() {
        greenButtonImage.setFitHeight(getDpY(473));
        greenButtonImage.setFitWidth(getDpX(473));
        greenButtonImage.setLayoutX(getDpX(399));
        greenButtonImage.setLayoutY(getDpY(67));
        greenButton.setPrefWidth(getDpX(438));
        greenButton.setPrefHeight(getDpY(238));
        greenButton.setLayoutX(getDpX(429));
        greenButton.setLayoutY(getDpY(194));
    }
    private void setMainMenuButton() {
        mainMenuButtonImage.setFitHeight(getDpY(150));
        mainMenuButton.setPrefWidth(getDpX(336));
        mainMenuButton.setPrefHeight(getDpY(150));
    }
    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        if(demonstration != null && demonstration.isAlive())
            demonstration.interrupt();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.getIcons().add(new Image(MainApplication.iconPath));
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }
    public void checkCorrectnessSubsequence(int indexButton) {
        if(!areEqual(indexButton, Objects.requireNonNull(userSubsequence.poll()))) {
            centerButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/CenterButton/CenterButtonLitUp.png"));
            try {
                AllButtonBlink allButtonBlink = new AllButtonBlink(rsc, greenButtonImage, redButtonImage,
                        blueButtonImage, yellowButtonImage);
                allButtonBlink.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PlaySound.stop();
            PlaySound.play(Sounds.PLAY_GAME_OVER);
            playersTurn = false;
        }
        else if(userSubsequence.isEmpty()) {
            totalScore++;
            record = true;
            setSaveResultButtonActive();
            score.setupScore(totalScore);
            continueDemonstration();
        }
    }
    private void editTextYourNameHasChanged() {
        editTextYourName.textProperty().addListener((observableValue, s, t1) -> {
            if(Objects.equals(editTextYourName.getText(), " "))
                editTextYourName.setText("");
            if(editTextYourName.getText().length() >= maxLength)
                editTextYourName.setText(editTextYourName.getText().substring(0, maxLength));
            setSaveResultButtonActive();
        });
    }
    @FXML
    private void gameButtonPressed(ActionEvent event) {
        if(demonstration != null && !demonstration.isAlive() && playersTurn) {
            switch(((Button)event.getSource()).getId()) {
                case "greenButton" -> checkCorrectnessSubsequence(0);
                case "redButton" -> checkCorrectnessSubsequence(1);
                case "blueButton" -> checkCorrectnessSubsequence(2);
                case "yellowButton" -> checkCorrectnessSubsequence(3);
            }
        }
    }
    @FXML
    private void gameButtonMouseEvent(MouseEvent event) {
        if(demonstration == null || !demonstration.isAlive()) {
            switch(event.getEventType().toString()) {
                case "MOUSE_RELEASED" -> buttonMouseReleased(((Button)event.getSource()).getId());
                case "MOUSE_PRESSED" -> buttonMousePressed(((Button)event.getSource()).getId());
            }
        }
    }
    @FXML
    private void functionButtonMouseEvent(MouseEvent event) {
        switch(event.getEventType().toString()) {
            case "MOUSE_PRESSED" -> buttonMousePressed(((Button)event.getSource()).getId());
            case "MOUSE_RELEASED" -> buttonMouseReleased(((Button)event.getSource()).getId());
            case "MOUSE_ENTERED" -> {
                if(((Button)event.getSource()).getId().equals("mainMenuButton"))
                    mainMenuButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/MainMenuButton/ButtonMenuReleased.png"));
                else if(record && !editTextYourName.getText().equals("") && ((Button)event.getSource()).getId().equals("saveResultButton"))
                    saveResultButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/SaveResultButton/SaveRelease.png"));
            }
            case "MOUSE_EXITED" -> {
                if(((Button)event.getSource()).getId().equals("mainMenuButton"))
                    mainMenuButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/MainMenuButton/ButtonMenuDefault.png"));
                else if(((Button)event.getSource()).getId().equals("saveResultButton"))
                    setSaveResultButtonActive();
            }
        }
    }
    private void buttonMousePressed(String id) {
        switch(id) {
            case "greenButton" -> {
                greenButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Green/GreenPressed.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_ZERO);
            }
            case "redButton" -> {
                redButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Red/RedPressed.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_ONE);
            }
            case "blueButton" -> {
                blueButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Blue/BluePressed.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_TWO);
            }
            case "yellowButton" -> {
                yellowButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Yellow/YellowPressed.png"));
                PlaySound.play(Sounds.PLAY_BUTTON_THREE);
            }
            case "mainMenuButton" -> {
                mainMenuButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/MainMenuButton/ButtonMenuPressed.png"));
                PlaySound.play(Sounds.PLAY_TAP);
            }
            case "centerButton" -> {
                centerButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/CenterButton/CenterButtonPressed.png"));
                PlaySound.play(Sounds.PLAY_TAP);
            }
            case "saveResultButton" -> {
                if(record && !editTextYourName.getText().equals("")) {
                    saveResultButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/SaveResultButton/SavePressed.png"));
                    PlaySound.play(Sounds.PLAY_SAVE);
                }
            }
        }
    }
    private void buttonMouseReleased(String id) {
        switch(id) {
            case "greenButton" -> greenButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Green/GreenDefault.png"));
            case "redButton" -> redButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Red/RedDefault.png"));
            case "blueButton" -> blueButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Blue/BlueDefault.png"));
            case "yellowButton" -> yellowButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/Yellow/YellowDefault.png"));
            case "mainMenuButton" -> mainMenuButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/MainMenuButton/ButtonMenuReleased.png"));
            case "centerButton" -> centerButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/CenterButton/CenterButtonDefault.png"));
            case "saveResultButton" -> {
                if(record && !editTextYourName.getText().equals(""))
                    saveResultButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/SaveResultButton/SaveRelease.png"));
            }
        }
    }
    @FXML
    private void beginSaveResult() {
        if(record && !editTextYourName.getText().equals("")) {
            SaveData saveData = new SaveData(totalScore, editTextYourName.getText());
            saveData.start();
        }
    }
    private void setSaveResultButtonActive() {
        if(record && !editTextYourName.getText().equals(""))
            saveResultButtonImage.setImage(new Image("file:" + rsc + "/Image/Simon/SaveResultButton/SaveDefault.png"));
    }
    double getDpX(double px) {
        return px * trueWidth;
    }
    double getDpY(double px) {
        return px * trueHeight;
    }
    public static boolean areEqual(int firstNumber, int secondNumber) {
        return firstNumber == secondNumber;
    }
}