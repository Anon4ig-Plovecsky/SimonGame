package com.example.simongame;

import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.util.Objects;
import javafx.fxml.FXML;
import java.net.URL;
import java.io.File;


public class MainMenu implements Initializable {
    private String sPathResource;
    @FXML
    private ImageView newGameButtonImageView;
    @FXML
    private ImageView exitButtonImageView;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private StackPane stackPaneMainMenu;
    @FXML
    private Button newGameButton;
    @FXML
    private Button exitButton;
    @FXML
    private ImageView logoGame;
    @FXML
    protected ImageView backgroundMenu;
    int width = (int)Screen.getPrimary().getBounds().getWidth();
    int height = (int)Screen.getPrimary().getBounds().getHeight();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showMenu();
    }
    void settingBackgroundMenu() {
        backgroundMenu.setFitWidth(width);
        backgroundMenu.setFitHeight(height);
        logoGame.setFitWidth(width);
        logoGame.setFitHeight(height);
    }
    void showMenu() {
        sPathResource = new File("src/main/resources/").getAbsolutePath();
        settingBackgroundMenu();
    }
    @FXML
    protected void newGameButtonOnPressed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SimonGame.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    protected void newGameButtonNotPointed() {
        newGameButtonImageView.setImage(new Image(sPathResource + "\\Image\\MainMenu\\NewGame.png"));
    }
    @FXML
    protected void newGameButtonOnPointed() {
        newGameButtonImageView.setImage(new Image(sPathResource + "\\Image\\MainMenu\\NewGamePointed.png"));
    }
    @FXML
    protected void newGameButtonPressed() {
        newGameButtonImageView.setImage(new Image(sPathResource + "\\Image\\MainMenu\\NewGamePressed.png"));
    }
    @FXML
    protected void NewGameButtonReleased() {
        newGameButtonImageView.setImage(new Image(sPathResource + "\\Image\\MainMenu\\NewGamePointed.png"));
    }
    @FXML
    protected void exitButtonOnPointed() {
        Image imageOnPointed = new Image(sPathResource + "\\Image\\MainMenu\\ExitPointed.png");
        exitButtonImageView.setImage(imageOnPointed);
    }
    @FXML
    protected void exitButtonNotPointed() {
        Image imageOnPointed = new Image(sPathResource + "\\Image\\MainMenu\\Exit.png");
        exitButtonImageView.setImage(imageOnPointed);
    }
    @FXML
    protected void exitButtonReleased() {
        exitButtonImageView.setImage(new Image(sPathResource + "\\Image\\MainMenu\\ExitPointed.png"));
    }
    @FXML
    protected void exitButtonPressed() {
        exitButtonImageView.setImage(new Image(sPathResource + "\\Image\\MainMenu\\ExitPressed.png"));
    }
    @FXML
    protected void exitButtonOnPressed() {
        Platform.exit();
    }
}
