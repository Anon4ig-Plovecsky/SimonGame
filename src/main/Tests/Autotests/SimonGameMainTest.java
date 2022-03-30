package Autotests;

import org.testfx.framework.junit5.ApplicationTest;
import com.example.simongame.MainApplication;
import javafx.scene.input.MouseButton;
import javafx.scene.input.KeyCode;
import org.testfx.api.FxToolkit;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class SimonGameMainTest extends ApplicationTest {
    private final String KEY_NEW_GAME_BUTTON = "#newGameButton";
    @Before
    public void setUp() throws Exception {
        ApplicationTest.launch(MainApplication.class);
    }
    @After
    public void afterEachTest() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});

    }
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
    @Test
    public void goToGameScene() {
        dropTo(KEY_NEW_GAME_BUTTON);
        clickOn(KEY_NEW_GAME_BUTTON);
    }
    //TODO: ↓
    public <T extends Node> T find(final String query) {
        return (T)lookup(query).queryAll().iterator().next();
    }
}