package JUnit;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import com.example.simongame.MainMenu;
import com.example.simongame.SaveData;
import java.util.stream.Stream;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.*;

class SaveDataTests {
    private File file;
    @BeforeEach
    void setUp() throws IOException {
        file = new File(MainMenu.path);
        boolean succesDeleteFile = file.delete();
    }
    @ParameterizedTest
    @MethodSource("resultFromGame")
    public void saveDataTest(String name, int score) throws FileNotFoundException {
        boolean actual;
        boolean expected = true;
        SaveData saveData = new SaveData(score, name);
        saveData.startRecord();
        JSONObject fileResult = (new JSONObject(new JSONTokener(new FileInputStream(file)))).getJSONArray(MainMenu.KEY_RESULTS).getJSONObject(0);
        actual = fileResult.getString(MainMenu.KEY_NAME).equals(name) && fileResult.getInt(MainMenu.KEY_SCORE) == score;
        Assertions.assertEquals(expected, actual);
    }
    @ParameterizedTest
    @MethodSource("resultsFromSomeGames")
    public void checkSortingResults(String[] names, int[] scores, int[] expectedScores) throws FileNotFoundException {
        for(int count = 0; count < names.length; count++) {
            SaveData saveData = new SaveData(scores[count], names[count]);
            saveData.startRecord();
        }
        JSONArray arrayResults = (new JSONObject(new JSONTokener(new FileInputStream(file)))).getJSONArray(MainMenu.KEY_RESULTS);
        int[] actualScores = new int[arrayResults.length()];
        for(int count = 0; count < arrayResults.length(); count++)
            actualScores[count] = arrayResults.getJSONObject(count).getInt(MainMenu.KEY_SCORE);
        Assertions.assertArrayEquals(expectedScores, actualScores);
    }
    @AfterEach
    void tearDown() {
        boolean succesDeleteFile = file.delete();
    }
    private static Stream<Arguments> resultFromGame() {
        return Stream.of(
            Arguments.of("Антон", 4),
            Arguments.of("Андрей", 13),
            Arguments.of("Name", 1)
        );
    }
    private static Stream<Arguments> resultsFromSomeGames() {
        return Stream.of(
            Arguments.of(new String[]{ "Владимир", "Александр", "Вячеслав" }, new int[]{ 3, 13, 6 }, new int[]{ 13, 6, 3 }),
            Arguments.of(new String[]{ "Антон", "Артём", "Анатолий" }, new int[]{ 16, 13, 3 }, new int[]{ 16, 13, 3 }),
            Arguments.of(new String[]{ "Екатерина", "Денис", "Олег" }, new int[]{ 9, 5, 13 }, new int[]{ 13, 9, 5 })
        );
    }
}