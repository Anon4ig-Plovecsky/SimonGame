package com.example.simongame;

import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.Objects;
import javafx.fxml.FXML;
import java.io.File;
import java.net.URL;

public class SimonGame implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String rsc;
    @FXML
    private ImageView centerButton;
    @FXML
    private ImageView body;
    @FXML
    private ImageView yellowButton;
    @FXML
    private ImageView greenButton;
    @FXML
    private ImageView redButton;
    @FXML
    private ImageView blueButton;
    @FXML
    private ImageView mainMenuButton;
    @FXML
    private Button greenBtn;
    @FXML
    private Button yellowBtn;
    @FXML
    private Button redBtn;
    @FXML
    private Button blueBtn;
    @FXML
    private Button centerBtn;
    @FXML
    private Button mainMenuBtn;
    private double width = (int)Screen.getPrimary().getBounds().getWidth();
    private double height = (int)Screen.getPrimary().getBounds().getHeight();
    private final double trueWidth = width / 1800.0;
    private final double trueHeight = height / 1125.0;
    private final double centerX = getDpX(1800.0 / 2.0);
    private final double centerY = getDpY(1125.0 / 2.0);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
        centerButton.setImage(new Image(rsc + "\\Image\\Simon\\CenterButton\\CenterButtonLitUp.png"));
    }
    private void setPath() {
        rsc = new File("src/main/resources/").getAbsolutePath();
    }
    private void setUpScene() {
        setPath();
        setBody();
        setMainMenuButton();
        setCenterButton();
        setYellowButton();
        setGreenButton();
        setRedButton();
        setBlueButton();
    }
    private void setBody() {
        body.setFitHeight(getDpY(1122));
        body.setFitWidth(getDpX(1122));
    }
    private void setCenterButton() {
        centerButton.setFitHeight(getDpY(354));
        centerButton.setFitWidth(getDpX(354));
        centerBtn.setPrefWidth(getDpX(257));
        centerBtn.setPrefHeight(getDpY(232));
    }
    private void setBlueButton() {
        blueButton.setFitHeight(getDpY(472));
        blueButton.setFitWidth(getDpX(473));
        blueButton.setLayoutX(getDpX(924));
        blueButton.setLayoutY(getDpY(594));
        blueBtn.setPrefWidth(getDpX(438));
        blueBtn.setPrefHeight(getDpY(238));
        blueBtn.setLayoutX(getDpX(935));
        blueBtn.setLayoutY(getDpY(689));
    }
    private void setRedButton() {
        redButton.setFitHeight(getDpY(473));
        redButton.setFitWidth(getDpX(473));
        redButton.setLayoutX(getDpX(930));
        redButton.setLayoutY((getDpY(72)));
        redBtn.setPrefWidth(getDpX(438));
        redBtn.setPrefHeight(getDpY(238));
        redBtn.setLayoutX(getDpX(935));
        redBtn.setLayoutY(getDpY(194));
    }
    private void setYellowButton() {
        yellowButton.setFitHeight(getDpY(472));
        yellowButton.setFitWidth(getDpX(472));
        yellowButton.setLayoutX(getDpX(402));
        yellowButton.setLayoutY(getDpY(594));
        yellowBtn.setPrefWidth(getDpX(438));
        yellowBtn.setPrefHeight(getDpY(238));
        yellowBtn.setLayoutX(getDpX(429));
        yellowBtn.setLayoutY(getDpY(689));
    }
    private void setGreenButton() {
        greenButton.setFitHeight(getDpY(473));
        greenButton.setFitWidth(getDpX(473));
        greenButton.setLayoutX(getDpX(399));
        greenButton.setLayoutY(getDpY(67));
        greenBtn.setPrefWidth(getDpX(438));
        greenBtn.setPrefHeight(getDpY(238));
        greenBtn.setLayoutX(getDpX(429));
        greenBtn.setLayoutY(getDpY(194));
    }
    private void setMainMenuButton() {
        mainMenuButton.setFitHeight(getDpY(150));
        mainMenuBtn.setPrefWidth(getDpX(336));
        mainMenuBtn.setPrefHeight(getDpY(150));
    }
    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void mainMenuButtonOnEntered() {
        mainMenuButton.setImage(new Image(rsc + "\\Image\\Simon\\MainMenuButton\\ButtonMenuReleased.png"));
    }
    @FXML
    public void mainMenuButtonOnExited() {
        mainMenuButton.setImage(new Image(rsc + "\\Image\\Simon\\MainMenuButton\\ButtonMenuDefault.png"));
    }
    @FXML
    protected void mainMenuButtonOnPressed() {
        mainMenuButton.setImage(new Image(rsc + "\\Image\\Simon\\MainMenuButton\\ButtonMenuPressed.png"));
    }
    @FXML
    protected void mainMenuButtonOnReleased() {
        mainMenuButton.setImage(new Image(rsc + "\\Image\\Simon\\MainMenuButton\\ButtonMenuReleased.png"));
    }
    double getDpX(double px) {
        return px * trueWidth;
    }
    double getDpY(double px) {
        return px * trueHeight;
    }
}
