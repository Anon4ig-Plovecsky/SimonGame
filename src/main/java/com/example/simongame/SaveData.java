package com.example.simongame;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;
import java.io.FileReader;

public class SaveData extends Thread {
    private JSONObject results;
    private JSONArray array;
    private final int totalScore;
    private final String name;
    private PrintWriter printWriter;
    public SaveData(int totalScore, String name) {
        this.totalScore = totalScore;
        this.name = name;
    }
    private void createJSONObject() {
        results = new JSONObject();
        array = new JSONArray();
        results.put(MainMenu.KEY_RESULTS, array);
    }
    private void startRecord() {
        try {
            results = (JSONObject) (new JSONParser().parse(new FileReader(MainMenu.path)));
            array = results.getJSONArray(MainMenu.KEY_RESULTS);
        } catch(IOException | ParseException e) {
            e.printStackTrace();
            createJSONObject();
        }
        JSONObject currentResult = new JSONObject();
        currentResult.put(MainMenu.KEY_NAME, name);
        currentResult.put(MainMenu.KEY_SCORE, totalScore);
        array.put(currentResult);
        if(array.length() >= 10) {

        }
        results.put(MainMenu.KEY_RESULTS, array);
        try {
            printWriter = new PrintWriter(MainMenu.path, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        super.run();
        startRecord();
    }
}