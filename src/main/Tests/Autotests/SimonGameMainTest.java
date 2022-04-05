package Autotests;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import com.example.simongame.MainApplication;
import org.testfx.matcher.base.NodeMatchers;
import javafx.scene.input.MouseButton;
import javafx.scene.input.KeyCode;
import org.testfx.api.FxToolkit;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

public class SimonGameMainTest extends ApplicationTest {
    private final String KEY_NEW_GAME_BUTTON = "#newGameButton";
    private final String KEY_CENTER_BUTTON = "#centerBtn";
    private final String KEY_TRUE_SUBSEQUENCE = "#trueSubsequenceLabel";
    private final String KEY_BUTTON_ZERO = "#greenBtn";
    private final String KEY_BUTTON_ONE = "#redBtn";
    private final String KEY_BUTTON_TWO = "#blueBtn";
    private final String KEY_BUTTON_THREE = "#yellowBtn";
    private final String KEY_SAVE_BUTTON = "#saveResultBtn";
    Pane root;
    Stage stageMain;
    @Override
    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        stageMain = stage;
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreenExitHint("");
        stage.setResizable(false);
        stage.show();
        stage.toFront();
    }
    @Before
    public void setUp() throws Exception {
//        ApplicationTest.launch(MainApplication.class);
        Stage stage = stageMain;
            start(stage);
    }
    @After
    public void afterEachTest() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    @Test
    public void goToGameScene() {
        dropTo(KEY_NEW_GAME_BUTTON);
        clickOn(KEY_NEW_GAME_BUTTON);
        dropTo(KEY_CENTER_BUTTON);
        clickOn(KEY_CENTER_BUTTON);
    }
    @Test
    public void setRecord() throws Exception {
        for(int i = 0; i < 5; i++) {
            Thread.sleep(2000 * (i + 10));
            @SuppressWarnings("unchecked")
            Label stringSubsequence = (Label) root.lookup(KEY_TRUE_SUBSEQUENCE);
            String[] trueSubsequence = stringSubsequence.getText().split(" ");
            for(String button : trueSubsequence)
                pressXButton(Integer.parseInt(button));
            /*TODO:
            verifyThat(KEY_TRUE_SUBSEQUENCE, NodeMatchers.isNotNull());
            String[] subsequence = */
        }
        dropTo(KEY_SAVE_BUTTON);
        clickOn(KEY_SAVE_BUTTON);

    }
    private void pressXButton(int index) throws Exception {
        String idButton = "";
        switch(index) {
            case 0 -> idButton = KEY_BUTTON_ZERO;
            case 1 -> idButton = KEY_BUTTON_ONE;
            case 2 -> idButton = KEY_BUTTON_TWO;
            case 3 -> idButton = KEY_BUTTON_THREE;
        }
        dropTo(idButton);
        clickOn(idButton);
        Thread.sleep(100);
    }
    //TODO: ↓
    public <T extends Node> T find(final String query) {
        return (T)lookup(query).queryAll().iterator().next();
    }
}