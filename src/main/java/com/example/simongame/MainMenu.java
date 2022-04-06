package com.example.simongame;

import javafx.scene.input.KeyCombination;
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
    private ImageView resultsButton;
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
    private ImageView logoGame;
    @FXML
    protected ImageView backgroundMenu;
    @FXML
    private Button newGameButton;
    @FXML
    private Button resultsBtn;
    private boolean haveResults = false;
    int height = (int)Screen.getPrimary().getBounds().getHeight();
    int width = (int)Screen.getPrimary().getBounds().getWidth();
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
        resultsButtonOnExited();
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
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
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
    private void resultsButtonOnEntered() {
        if(haveResults)
            resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonRelease.png"));
    }
    @FXML
    private void resultsButtonOnExited() {
        if(haveResults)
            resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonDefault.png"));
    }
    @FXML
    private void resultsButtonOnPressed() {
        if(haveResults) {
            resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonPressed.png"));
            PlaySound.play(Sounds.PLAY_TAP);
        }
    }
    @FXML
    private void resultsButtonOnReleased() {
        if(haveResults)
            resultsButton.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ResultsButtonRelease.png"));
    }
    @FXML
    protected void newGameButtonNotPointed() {
        String path = "file:" + sPathResource + "/Image/MainMenu/NewGame.png";
        Image newButton = new Image(path);
        newGameButtonImageView.setImage(newButton);
    }
    @FXML
    protected void newGameButtonOnPointed() {
        newGameButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/NewGamePointed.png"));
    }
    @FXML
    protected void newGameButtonPressed() {
        PlaySound.play(Sounds.PLAY_TAP);
        newGameButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/NewGamePressed.png"));
    }
    @FXML
    protected void NewGameButtonReleased() {
        newGameButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/NewGamePointed.png"));
    }
    @FXML
    protected void exitButtonOnPointed() {
        Image imageOnPointed = new Image("file:" + sPathResource + "/Image/MainMenu/ExitPointed.png");
        exitButtonImageView.setImage(imageOnPointed);
    }
    @FXML
    protected void exitButtonNotPointed() {
        Image imageOnPointed = new Image("file:" + sPathResource + "/Image/MainMenu/Exit.png");
        exitButtonImageView.setImage(imageOnPointed);
    }
    @FXML
    protected void exitButtonReleased() {
        exitButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ExitPointed.png"));
    }
    @FXML
    protected void exitButtonPressed() {
        PlaySound.play(Sounds.PLAY_TAP);
        exitButtonImageView.setImage(new Image("file:" + sPathResource + "/Image/MainMenu/ExitPressed.png"));
    }
    @FXML
    protected void exitButtonOnPressed() {
        Platform.exit();
    }
}
