package Autotests;

import org.testfx.framework.junit5.ApplicationTest;
import com.example.simongame.MainApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import com.example.simongame.MainMenu;
import javafx.scene.input.MouseButton;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import java.io.FileInputStream;
import org.json.JSONTokener;
import org.json.JSONObject;
import java.nio.file.Paths;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.HashMap;
import java.util.Map;

public class AutotestSimonGameTest extends ApplicationTest {
    private final String KEY_NEW_GAME_BUTTON = "#newGameButton";
    private final String KEY_CENTER_BUTTON = "#centerBtn";
    private final String KEY_TRUE_SUBSEQUENCE = "#trueSubsequenceLabel";
    private final String KEY_BUTTON_ZERO = "#greenBtn";
    private final String KEY_BUTTON_ONE = "#redBtn";
    private final String KEY_BUTTON_TWO = "#blueBtn";
    private final String KEY_BUTTON_THREE = "#yellowBtn";
    private final String KEY_SAVE_BUTTON = "#saveResultBtn";
    private final String KEY_SCORE_IMAGE = "#firstNumberScore";
    private final String KEY_EDIT_TEXT = "#editTextYourName";
    private final String KEY_MAIN_MENU = "#mainMenuBtn";
    private final String KEY_MAIN_MENU_RESULT = "#MainMenu";
    private final String KEY_RESULT_BUTTON = "#resultsBtn";
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(MainApplication.class);
    }
    @AfterEach
    public void afterEachTest() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
        press(KeyCode.ESCAPE); release(KeyCode.ESCAPE);
    }
    @Test
    public void getNewScore() throws Exception {
        dropTo(KEY_NEW_GAME_BUTTON);
        clickOn(KEY_NEW_GAME_BUTTON);
        dropTo(KEY_CENTER_BUTTON);
        clickOn(KEY_CENTER_BUTTON);
        for(int i = 0; i < 5; i++) {
            Thread.sleep(2000 * (i + 1));
            Label stringSubsequence = find(KEY_TRUE_SUBSEQUENCE);
            String[] trueSubsequence = stringSubsequence.getText().split(" ");
            for(String button : trueSubsequence)
                pressXButton(Integer.parseInt(button));
        }
        ImageView score = find(KEY_SCORE_IMAGE);
        String pathToFiveScore = Paths.get("src/main/resources/Image/Simon/Numbers/Five.png").toAbsolutePath().toString();
        String url = score.getImage().getUrl();
        url = url.substring(5);
        String actualPathScore = Paths.get(url).toString();
        Assertions.assertEquals(pathToFiveScore, actualPathScore);
        dropTo(KEY_MAIN_MENU);
        clickOn(KEY_MAIN_MENU);
    }
    @Test
    public void saveResult() throws Exception {
        dropTo(KEY_NEW_GAME_BUTTON);
        clickOn(KEY_NEW_GAME_BUTTON);
        dropTo(KEY_CENTER_BUTTON);
        clickOn(KEY_CENTER_BUTTON);
        Thread.sleep(2000);
        Label stringSubsequence = find(KEY_TRUE_SUBSEQUENCE);
        String[] trueSubsequence = stringSubsequence.getText().split(" ");
        pressXButton(Integer.parseInt(trueSubsequence[0]));
        dropTo(KEY_EDIT_TEXT);
        clickOn(KEY_EDIT_TEXT);
        press(KeyCode.SHIFT);
        press(KeyCode.A); release(KeyCode.A);
        release(KeyCode.SHIFT);
        press(KeyCode.N); release(KeyCode.N);
        press(KeyCode.T); release(KeyCode.T);
        press(KeyCode.O); release(KeyCode.O);
        press(KeyCode.N); release(KeyCode.N);
        press(KeyCode.Y); release(KeyCode.Y);
        dropTo(KEY_SAVE_BUTTON);
        clickOn(KEY_SAVE_BUTTON);
        dropTo(KEY_MAIN_MENU);
        clickOn(KEY_MAIN_MENU);
        dropTo(KEY_RESULT_BUTTON);
        clickOn(KEY_RESULT_BUTTON);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Antony", 1);
        Map<String, Integer> actual = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(MainMenu.path);
        JSONObject result = (new JSONObject(new JSONTokener(fileInputStream))).getJSONArray(MainMenu.KEY_RESULTS).getJSONObject(0);
        fileInputStream.close();
        actual.put(result.getString(MainMenu.KEY_NAME), result.getInt(MainMenu.KEY_SCORE));
        press(KeyCode.ESCAPE); release(KeyCode.ESCAPE);
        dropTo(KEY_MAIN_MENU_RESULT);
        clickOn(KEY_MAIN_MENU_RESULT);
        Assertions.assertEquals(expected, actual);
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
        press(MouseButton.PRIMARY);
        Thread.sleep(100);
        release(MouseButton.PRIMARY);
    }
    public <T extends Node> T find(final String query) {
        return (T)lookup(query).queryAll().iterator().next();
    }
}